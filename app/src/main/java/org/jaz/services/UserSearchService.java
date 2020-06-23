package org.jaz.services;

import org.jaz.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserSearchService {

    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Optional<User> findUser(String username){
        return em.createQuery("from User where username= :username",User.class)
                .setParameter("username", username)
                .getResultList().stream()
                .findFirst();
    }
}
