package application.server.persistence.DTOs;

import lombok.Data;

@Data
public class AuthRequest {
    String username;
    String password;
    public AuthRequest() {}

    public AuthRequest(String username,String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }







}

