package org.acme.domain.models;

import java.util.UUID;

public class UserModel {
    private UUID uuid;
    private String name_full;
    private String phone;
    private String email;
    private String photo;
    private String username;
    private String password;
    private String role;

    public UserModel(){}

    public UserModel(UUID uuid, String name_full, String phone, String email, String photo, String username, String password, String role){
        this.uuid = uuid;
        this.name_full = name_full;
        this.phone = phone;
        this.email = email;
        this.photo = photo;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static UserModel init(UUID uuid, String name_full, String phone, String email, String photo, String username, String password, String role){
        return new UserModel(uuid, name_full, phone, email, photo, username, password, role);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName_full() {
        return name_full;
    }

    public void setName_full(String name_full){
        this.name_full = name_full;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public String getUsername(){
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

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
