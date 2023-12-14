package edu.hw10.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.HashMap;

public class MyProxy {

    private static final String PATH
        = Path.of("src", "main", "resources", "cache.txt").toString();
    private static final HashMap<Long, Long> CACHE = readFromFile();

    private static HashMap<Long, Long> readFromFile() {
        File file = new File(PATH);
        try (var reader = new ObjectInputStream(new FileInputStream(file))) {
            return (HashMap<Long, Long>) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static class FibCalc implements FibCalculator {

        @Override
        @SuppressWarnings("MagicNumber")
        public long fib(long number) {
            if (number <= 2) {
                CACHE.put(number, 1L);
                return 1;
            }

            long f = 1;
            long s = 1;
            for (int i = 3; i <= number; ++i) {
                long t = f;
                f = s;
                s = s + t;
            }

            CACHE.put(number, s);
            return s;
        }
    }

    public static class Handler implements InvocationHandler {
        private final FibCalculator original;

        public Handler(FibCalculator original) {
            this.original = original;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                var ret = CACHE.get((Long) args[0]);

                if (ret == null) {
                    ret = (Long) method.invoke(original, args);
                }

                if (method.getAnnotation(Cache.class).persist()) {
                    writeToFile();
                }

                return ret;
            }
            return method.invoke(original, args);
        }

        private void writeToFile() {
            File file = new File(PATH);
            try (var writer = new ObjectOutputStream(new FileOutputStream(file))) {
                writer.writeObject(CACHE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
