package org.jaz.domain;

import org.jaz.webapp.register.RegisterRequest;

import java.util.Date;

public class User {
    private long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String password;

    public User(String username, String email, String firstName, String lastName, Date birthDate, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.password = password;
    }
    public User(RegisterRequest registerRequest) {
        this.username = registerRequest.getUsername();
        this.email = registerRequest.getEmail();
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
        this.birthDate = registerRequest.getBirthDate();
        this.password = registerRequest.getPassword();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                '}';
    }
}
