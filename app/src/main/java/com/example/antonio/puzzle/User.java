package com.example.antonio.puzzle;

/**
 * Created by antonio on 8/1/16.
 */
public class User {

    String username, password;
    int age;

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
