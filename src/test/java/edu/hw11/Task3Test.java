package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

public class Task3Test {
    @Test
    void fibCreating() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var res =
            new ByteBuddy()
                .subclass(Object.class)
                .name("Fib")
                .modifiers(Opcodes.ACC_PUBLIC)
                .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
                .withParameter(int.class)
                .intercept(new Implementation.Simple(new FibAppender()))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        System.out.println(res.getMethod("fib", int.class).invoke(null,15));
    }

    static class FibAppender implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            methodVisitor.visitCode();

            methodVisitor.visitIntInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.IRETURN);
            methodVisitor.visitMaxs(2, 1);

            methodVisitor.visitEnd();

            return new Size(2, 1);
        }
    }
}
