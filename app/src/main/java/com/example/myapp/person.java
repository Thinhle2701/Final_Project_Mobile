package com.example.myapp;

public class person {


    public int id;
    public String username;
    public String password;
    public int score;

    public person(int count_number_user, String usename, String pasword) {
        this.id = count_number_user;
        this.username = usename;
        this.password = pasword;
        this.score = 0;
    }

    public void set_score(int x)
    {
        this.score = x;
    }



};
