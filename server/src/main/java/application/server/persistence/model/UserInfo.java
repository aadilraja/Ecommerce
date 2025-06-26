package application.server.persistence.model;

import jakarta.persistence.*;
import application.server.persistence.model.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name="user_id", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long user_id;
    @Column(name="user_name", nullable=false,unique=true)
    private  String user_name;
    @Column(name="user_password", nullable=false)
    private  String user_password;
    @Column(name="user_dp_path")
    private  String user_dp_path;
    @Column(name="user_email",nullable = false)
    private  String userEmail;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "role_id")
    private Role role;



    @Column(name = "enabled",nullable = false)
    private  boolean enabled;

    public UserInfo() {}
    public UserInfo(String userEmail, String user_name, String user_password) {
        super();
        this.userEmail = userEmail;
        this.user_name = user_name;
        this.user_password = user_password;
        this.enabled = false;

    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String user_email) {
        this.userEmail = user_email;
    }


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_dp_path() {
        return user_dp_path;
    }

    public void setUser_dp_path(String user_dp_path) {
        this.user_dp_path = user_dp_path;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
