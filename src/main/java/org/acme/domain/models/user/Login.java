package org.acme.domain.models.user;

import lombok.extern.slf4j.Slf4j;

public class Login {
    private String username;
    private String password;

    public Login(){}

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    public static Login create(String username, String password){
        return new Login(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
