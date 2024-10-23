package lv5.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime
@Target(ElementType.FIELD) // This annotation is applicable to fields
public @interface LengthCheck {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
}