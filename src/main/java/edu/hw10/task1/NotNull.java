package edu.hw10.task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(value = ElementType.PARAMETER)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotNull {
    String value() default "";
}
