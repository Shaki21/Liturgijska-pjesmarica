package com.ante.LiturgijskaPjesmarica.models;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class User {
    private String username;
    private String profilePicture;
    public String email;
    public String password;
    public String confirmPassword;

    public User() {
    }

    public User(String username, String email, String profilePicture) {
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public User(String username, String profilePicture, String email, String password, String confirmPassword) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
