package com.ante.LiturgijskaPjesmarica.models;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class User {
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    public User(){}

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
