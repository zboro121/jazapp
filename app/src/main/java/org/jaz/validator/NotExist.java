package org.jaz.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExist {
    String message() default "White spaces error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}