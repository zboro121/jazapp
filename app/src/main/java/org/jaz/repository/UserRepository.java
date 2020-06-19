package org.jaz.repository;


import org.jaz.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository{
    private final List<User> users = new ArrayList<>();
    private long counter = 1;

    public  List<User> getUsers(){
        return users;
    }

    public User getUserByUsername(String username){
        for (User user: users){
            if (user.getUsername().equalsIgnoreCase(username))
                return user;
        }
        return null;
    }
    public Boolean checkIfUserExists(String username){
        for (User user: users){
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
    public Boolean checkIfUserAndPasswordAreCorrect(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;

    }
    public void add(User user){
        user.setId(counter++);
        users.add(user);
    }
    public int count(){
        return users.size();
    }
    public User getUserByIndex(int index){
        return users.get(index);
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                ", counter=" + counter +
                '}';
    }
}


