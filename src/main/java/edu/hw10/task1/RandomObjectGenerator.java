package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class RandomObjectGenerator {
    public static final Random random = new Random();

    public Object nextObject(Class<?> curClass) {
        try {
            var constructor = curClass.getConstructor(String.class, int.class);
            var annotations = constructor.getParameterAnnotations();

            var params = getObject(annotations);

            return constructor.newInstance(params.getKey(), params.getValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Object nextObject(Class<?> curClass, String fabricMethodName)
        throws Throwable {

        var fabricHandle = curClass.getMethod(fabricMethodName, String.class, int.class);
        var annotations = fabricHandle.getParameterAnnotations();

        var params = getObject(annotations);

        return fabricHandle.invoke(curClass, params.getKey(), params.getValue());
    }

    private Map.Entry<String, Integer> getObject(Annotation[][] annotations) {
        int min = 0;
        int max = 100;

        var name = UUID.randomUUID().toString();
        int age;

        for (var paramAnnotations : annotations) {
            for (var annotation : paramAnnotations) {
                if (annotation.annotationType().equals(NotNull.class) && name == null) {
                    while (name == null) {
                        name = UUID.randomUUID().toString();
                    }
                } else if (annotation.annotationType().equals(Min.class)) {
                    min = ((Min) annotation).value();
                } else if (annotation.annotationType().equals(Max.class)) {
                    max = ((Max) annotation).value();
                }
            }
        }
        age = random.nextInt(min, max + 1);
        return Map.entry(name, age);
    }
}
