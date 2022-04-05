package com.example.myapp;


public class list_account {
    public com.example.myapp.person[] list;
    public int number_account;

    public list_account()
    {
        this.list = new com.example.myapp.person[1000];
        this.number_account = 0;
    }

    public boolean add_account(com.example.myapp.person a)
    {
        this.list[this.number_account] = a;
        this.number_account++;
        return true;
    }

    public boolean check_login(String name,String pass)
    {
        boolean check = false;
        int id = -1;
        for (int i = 0;i < this.number_account;i++)
        {
            if (name.equals(this.list[i].username))
            {
                id = i;
            }
        }

        if (id == -1)
        {
            check = false;
        }
        else
        {
            if (pass.equals(this.list[id].password))
            {
                check = true;
            }
            else
            {
                check = false;
            }

        }

        return check;
    }

    public boolean Check_User_Name(String name)
    {
        boolean check = true;
        for (int i = 0;i < this.number_account;i++)
        {
            if (this.list[i].username == name)
            {
                check = false;
            }
        }
        return check;
    }

}
