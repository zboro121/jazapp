package org.jaz.webapp.register;

import org.jaz.validator.UniqueUserName;
import org.jaz.validator.WhiteSpace;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;



@Named
@RequestScoped
public class RegisterRequest {

    @UniqueUserName(message = "Username is already taken.")
    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    @Size(message = "Username too long.", max=25)
    @Pattern(regexp = "^[a-z0-9]+$", message = "Username can contain only numbers and small characters")
    private String username;

    @NotEmpty(message = "Field can not be empty.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    @Pattern(regexp = "[a-zA-Z]+",message = "First name can contain only letters.")
    private String firstName;

    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    @Pattern(regexp = "[a-zA-Z]+",message = "Last name can contain only letters.")
    private String lastName;

    private Date birthDate;

    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    @Size(message = "Password too long.", max=25)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]|.*[0-9]).*$", message = "Password must contain small and large letter and at least one number or special character.")
    private String password;

    @NotEmpty(message = "Field can not be empty.")
    @WhiteSpace(message = "Field can not contain whitespaces.")
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
