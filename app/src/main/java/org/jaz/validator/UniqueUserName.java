package org.jaz.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserName {
    String message() default "White spaces error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}