package com.example.myapp;

public class user {
   public String userid;
   public String account;
   public String password;
   public String account_type;
   public int point;

   public  user(String username,String password)
   {
      this.account = username;
      this.password = password;
   }

   public  user()
   {

   }
}
