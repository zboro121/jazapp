package org.jaz.validator;


import org.jaz.domain.User;
import org.jaz.services.UserSearchService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class NotExistValidator implements ConstraintValidator<NotExist, String> {

    @Inject
    private UserSearchService userSearchService;

    @Override
    public void initialize(NotExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userSearchService.findUser(name);
        return user.isPresent() || name.equals("");
    }
}