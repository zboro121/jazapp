package org.jaz.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WhiteSpaceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface WhiteSpace {
    String message() default "White spaces error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
