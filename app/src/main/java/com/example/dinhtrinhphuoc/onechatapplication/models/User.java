package com.example.dinhtrinhphuoc.onechatapplication.models;

/**
 * Created by TrinhPhuoc on 11-Nov-16.
 */

public class User {
    public String uid;
    public String email;
    public String firebaseToken;

    public User() {
    }

    public User(String uid, String email, String firebaseToken) {
        this.uid = uid;
        this.email = email;
        this.firebaseToken = firebaseToken;
    }
}
