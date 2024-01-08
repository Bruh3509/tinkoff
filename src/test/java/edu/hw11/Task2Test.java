package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.implementation.MethodDelegation.to;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @Disabled
    void testRuntimeBehChange() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(Sum.class)
            .method(named("sum")).intercept(to(Mult.class))
            .make()
            .load(Mult.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        assertThat(Sum.sum(1,2)).isEqualTo(2);
    }

    static class Sum {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    static class Mult {
        public static int multiplication(int a, int b) {
            return a * b;
        }
    }
}
