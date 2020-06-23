package org.jaz.validator;


import org.jaz.domain.User;
import org.jaz.services.UserSearchService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

//    @Inject
//    private UserRepository userRepository;
    @Inject
    private UserSearchService userSearchService;

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
//        User user = userRepository.getUserByUsername(name);
        Optional<User> user = userSearchService.findUser(name);
        return user.isEmpty();
    }
}