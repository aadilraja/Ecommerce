package application.server.persistence.DTOs;


import application.server.Validation.annotation.PasswordMatches;
import application.server.Validation.annotation.ValidateEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches
public class UserCreateDTO {

    @NotNull
    @NotEmpty(message = "User Name must not be empty")
    private String userName;
    @NotNull
    @NotEmpty(message = "User Password must not be empty")
    private String userPassword;
    @NotNull
    @NotEmpty(message = "User Matching Password must not be empty")
    private String userMatchingPassword;
    @NotNull
    @NotEmpty(message = "User Email must not be empty")
    @ValidateEmail
    private String userEmail;


    public UserCreateDTO() {}

    public UserCreateDTO(String userName, String userPassword, String userEmail,String userMatchingPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userMatchingPassword = userMatchingPassword;
    }

    // Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public  String getUserMatchingPassword() {
        return userMatchingPassword;
    }

    public void setUserMatchingPassword (String userMatchingPassword) {
        this.userMatchingPassword = userMatchingPassword;
    }
}
