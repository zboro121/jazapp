package org.jaz;

import org.jaz.domain.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.rmi.server.UID;
import java.util.UUID;

@SessionScoped
public class UserContext implements Serializable {
    private static final long serialVersionUID = 1L;
    private long userId = 0;
    private String firstName;
    private String lastName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
