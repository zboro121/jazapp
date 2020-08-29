package org.jaz.domain;

import org.jaz.webapp.register.RegisterRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="birthDate")
    private Date birthDate;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @OneToMany(mappedBy = "owner")
    private List<Auction> auctions = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String firstName, String lastName, Date birthDate, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.password = password;
        this.role = "DEFAULT";
    }
    public User(RegisterRequest registerRequest) {
        this.username = registerRequest.getUsername();
        this.email = registerRequest.getEmail();
        this.firstName = registerRequest.getFirstName();
        this.lastName = registerRequest.getLastName();
        this.birthDate = registerRequest.getBirthDate();
        this.password = registerRequest.getPassword();
        this.role = "DEFAULT";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
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
