package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String[] stringArray = getIntent().getStringArrayExtra("key");
        int number_acc = getIntent().getIntExtra("number", 0);
        Button register_butt = (Button) findViewById(R.id.bt_register);
        register_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView username = (TextView) findViewById(R.id.username_regis);
                TextView password = (TextView) findViewById(R.id.password_regis);
                TextView confirm_password = (TextView) findViewById(R.id.confirm_password_regis);
                String UseName = username.getText().toString();

                String PassWord = password.getText().toString();
                String ConfirmPassWord = confirm_password.getText().toString();
                boolean check_name = check_username(stringArray,UseName,number_acc);
                if (check_name == true)
                {

                    if (PassWord.equals(ConfirmPassWord))
                    {
                        Intent intent = new Intent(Register_activity.this,MainActivity.class);

                        call_api_register(UseName,PassWord);
                        Toast.makeText(Register_activity.this,"Register Successful ",Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Register_activity.this,"Password and Confirm Password is not match ",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Register_activity.this,"Register Unsuccessful (Username is exist in system) ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button back_to_login = (Button) findViewById(R.id.button_back);
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public  boolean check_username(String [] a,String name,int length) {
        boolean check = true;
        for (int i = 0;i < length;i++) {
            if (a[i].equals(name))
            {
                check = false;
            }
        }
        return check;
    }

    private void call_api_register(String username,String password)
    {
        Body_User User = new Body_User(username,password);
        ApiService.apiService.RegisterUser(User).enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                Toast.makeText(Register_activity.this,"call api successful",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(Register_activity.this,"call api unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });
    }









}