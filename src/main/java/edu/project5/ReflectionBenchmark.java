package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(30))
            .build();

        new Runner(options).run();
    }

    record Student(String name, String surname) {
    }

    private Student student;
    private Method method;
    private MethodHandle handler;
    private Supplier<String> lambda;

    @Setup
    public void setup()
        throws Throwable {
        student = new Student("Ivan", "Utrushkin");

        method = ReflectionBenchmark.Student.class.getMethod("name");

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class);
        handler = lookup.findVirtual(Student.class, "surname", type);

        var site = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            lookup.unreflect(method),
            MethodType.methodType(String.class)
        );

        lambda = (Supplier<String>) site.getTarget().bindTo(student).invoke();
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name();
        blackhole.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole blackhole) throws Throwable {
        String surname = (String) handler.invoke(student);
        blackhole.consume(surname);
    }

    @Benchmark
    public void lambdaMeta(Blackhole blackhole) throws Throwable {
        String name = lambda.get();
        blackhole.consume(name);
    }
}
