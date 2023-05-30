package com.example.pharmacyproject.model;

import java.io.Serializable;

public class User extends BaseModel implements Serializable {
    private String identity_number;
    private String password;
    private String user_role;

    public User() {
    }

    public User(int id, String identity_number, String password, String user_role) {
        super(id);
        this.identity_number = identity_number;
        this.password = password;
        this.user_role = user_role;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
