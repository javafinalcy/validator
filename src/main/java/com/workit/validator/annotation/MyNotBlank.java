package com.workit.validator.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyNotBlank {

    String message() default "{javax.validation.constraints.NotBlank.message}";

}
