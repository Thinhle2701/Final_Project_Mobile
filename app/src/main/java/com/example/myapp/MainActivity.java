
package com.example.myapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.contract.ActivityResultContract;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.result.contract.ActivityResultContracts;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private  static final int MY_REQUEST_CODE = 100;

    String name = "admin";
    String password = "admin";

    public  static list_account a = new list_account();

     person x = new person(0,name,password);

    public boolean check = a.add_account(x);
    int count_num = a.number_account;
    String s = String.valueOf(count_num);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test oop

        x.score = 1000;
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        Button login_btn = (Button) findViewById(R.id.loginbtn);
        Button register_but = (Button) findViewById(R.id.button_register);

        register_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Register_activity.class);
                String [] temp = new String[1000];
                for (int i = 0;i < a.number_account;i++)
                {
                    temp[i] = a.list[i].username;
                }
                intent.putExtra("number",a.number_account);
                intent.putExtra("key",temp);
                Toast.makeText(MainActivity.this,"Start to Register ",Toast.LENGTH_SHORT).show();
//                startActivity(intent);
                startActivityForResult(intent,MY_REQUEST_CODE);
            }


        });






        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pas = password.getText().toString();
                click_call_api(name,pas);
            }
        });

    }

    public void openHomePage()
    {


    }















    //register


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MY_REQUEST_CODE == requestCode && RESULT_OK == resultCode)
        {
        }
    }

    private void click_call_api(String username,String password)
    {
        Body_User x = new Body_User(username,password);
        ApiService.apiService.loginUser(x).enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                Toast.makeText(MainActivity.this,"call api successful",Toast.LENGTH_SHORT).show();
                user test = response.body();
                String user_id = response.body().userid;
                Log.d(user_id, "onResponse: ");
                Intent intent = new Intent(MainActivity.this,Home_Screen.class);
                intent.putExtra("score",test.point);
                intent.putExtra("user_id",user_id);

                startActivity(intent);
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(MainActivity.this,"call api Unsuccessful",Toast.LENGTH_SHORT).show();

            }
        });
    }
}