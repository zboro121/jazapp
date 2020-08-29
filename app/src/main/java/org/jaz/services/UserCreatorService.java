package org.jaz.services;

import org.jaz.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserCreatorService {

    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        em.persist(user);
    }

}
