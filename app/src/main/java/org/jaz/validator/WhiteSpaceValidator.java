package org.jaz.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhiteSpaceValidator implements ConstraintValidator<WhiteSpace, String> {



    @Override
    public void initialize(WhiteSpace constraintAnnotation) {
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.contains(string," ");
    }
}