package org.jaz.validator;


import org.jaz.domain.User;
import org.jaz.repository.UserRepository;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    @Inject
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.getUserByUsername(name);
        return user==null;
    }
}