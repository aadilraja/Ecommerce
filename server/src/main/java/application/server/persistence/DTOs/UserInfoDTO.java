package application.server.persistence.DTOs;


public class UserInfoDTO {

    private Long userId;
    private String userName;
    private String userDpPath;
    private  String user_email;

    public UserInfoDTO() {}

    public UserInfoDTO(Long userId, String userName, String userDpPath,String user_email) {
        this.userId = userId;
        this.userName = userName;
        this.userDpPath = userDpPath;
        this.user_email = user_email;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDpPath() {
        return userDpPath;
    }

    public void setUserDpPath(String userDpPath) {
        this.userDpPath = userDpPath;
    }
}

