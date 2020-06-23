package org.jaz.webapp.login;

import org.jaz.UserContext;
import org.jaz.domain.User;
import org.jaz.services.UserSearchService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private UserSearchService userSearchService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(@Valid LoginRequest loginRequest){

        System.out.println("Tried to log in" + loginRequest.toString());

        Optional<User> user = userSearchService.findUser(loginRequest.getUsername());
        if (user.isPresent()){
            if (passwordEncoder.matches(loginRequest.getPassword(),user.get().getPassword())) {
                UserContext userContext = new UserContext();

                userContext.setUserId(user.get().getId());
                userContext.setFirstName(user.get().getFirstName());
                userContext.setLastName(user.get().getLastName());

                FacesContext context = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                session.setAttribute("user", userContext);

                return "/index.xhtml?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash()
                .put("error-user-not-exist","Incorrect username or password");
        return "/login.xhtml?faces-redirect=true";
    }
}
