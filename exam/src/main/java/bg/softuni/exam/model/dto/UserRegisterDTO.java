package bg.softuni.exam.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotBlank(message = "Username not be blank!")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters !")
    private String username;
    @NotBlank(message = "Email not be blank!")
    @Email(message = "Email must be valid!")
    private String email;
    @NotBlank(message = "Password not be blank!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @NotBlank(message = "Confirm password not be blank!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;

    public UserRegisterDTO() {
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
}
