package com.example.myapp;

public class list_question {
    public question[] list_q;
    public int number_question;

    public list_question()
    {
        this.list_q = new com.example.myapp.question[1000];
        this.number_question = 0;
    }

    public boolean add_question(com.example.myapp.question a)
    {
        this.list_q[this.number_question] = a;
        this.number_question++;
        return true;
    }
}
