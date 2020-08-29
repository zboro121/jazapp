package org.jaz.services;

import org.jaz.UserContext;
import org.jaz.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserSearchService {

    @PersistenceContext
    private EntityManager em;

    public Optional<User> findUser(String username){
        return em.createQuery("from User where username= :username",User.class)
                .setParameter("username", username)
                .getResultList().stream()
                .findFirst();
    }
    public Optional<User> findUser(long id){
        return em.createQuery("from User where id= :id",User.class)
                .setParameter("id", id)
                .getResultList().stream()
                .findFirst();
    }
    public Optional<User> getUserFromSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        UserContext userContext = (UserContext) session.getAttribute("user");
        return findUser(userContext.getUserId());
    }
}
