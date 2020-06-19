package org.jaz.webapp.register;

import org.jaz.domain.User;
import org.jaz.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

@Named
@RequestScoped
public class RegisterController {
    @Inject
    private UserRepository userRepository;

    public String register(@Valid RegisterRequest registerRequest){
        System.out.println("Tried to register" + registerRequest.toString() );

        //format string to: 1) first letter uppercase, 2)rest lowercase
        registerRequest.setFirstName(formatString(registerRequest.getFirstName()));
        registerRequest.setLastName(formatString(registerRequest.getLastName()));

        User user = new User(registerRequest);
        userRepository.add(user);

        return "/login.xhtml?faces-redirect=true";
    }

    private String formatString(String name){
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
