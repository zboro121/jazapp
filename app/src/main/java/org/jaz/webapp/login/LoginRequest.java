package org.jaz.webapp.login;

import org.jaz.validator.NotExist;
import org.jaz.validator.WhiteSpace;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Named
@RequestScoped
public class LoginRequest {

    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    @NotExist(message = "Wrong username or password." )
    @Pattern(regexp = "^[a-z0-9]+$", message = "Username can contain only numbers and small characters")
    private String username;

    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
