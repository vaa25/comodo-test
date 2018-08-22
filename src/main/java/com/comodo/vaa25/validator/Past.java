package com.comodo.vaa25.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastValidator.class)
@Documented
public @interface Past {

    String message() default "{javax.validation.constraints.Past.message}"; //Default javax Past message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}