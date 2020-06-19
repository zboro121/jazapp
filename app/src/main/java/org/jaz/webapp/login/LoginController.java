package org.jaz.webapp.login;

import org.jaz.UserContext;
import org.jaz.domain.User;
import org.jaz.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Named
@RequestScoped
public class LoginController {
    @Inject
    UserRepository userRepository;

    public String login(@Valid LoginRequest loginRequest){
        System.out.println("Tried to log in" + loginRequest.toString() );
        if (userRepository.checkIfUserAndPasswordAreCorrect(loginRequest.getUsername(),loginRequest.getPassword())){

            UserContext userContext = new UserContext();
            User user = userRepository.getUserByUsername(loginRequest.getUsername());

            userContext.setUserId(user.getId());
            userContext.setFirstName(user.getFirstName());
            userContext.setLastName(user.getLastName());

            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("user",userContext);


            return "/index.xhtml?faces-redirect=true";
        }
        return "/login.xhtml?faces-redirect=true";
    }
}
