package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class test_api extends AppCompatActivity {
    TextView a;
    Button rq_api;
    user temp;
    ImageView imageResult;
    Button clear_url;
    EditText url;

    int question_id;
    String question_name;
    String img;
    String result;
    IMG image;

    Button call_api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);
        a = (TextView) findViewById(R.id.text_view_api);
        rq_api = (Button) findViewById(R.id.button_call_api);
        clear_url = (Button) findViewById(R.id.button_clear);
        url = (EditText)findViewById(R.id.editText_URL) ;
        imageResult = (ImageView)findViewById(R.id.image_load);
        call_api = (Button)findViewById(R.id.button_api);




        rq_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String urlLink = a.getText().toString();

                if (urlLink.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"please fill URL",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoadImage loadImage = new LoadImage(imageResult);
                    loadImage.execute(urlLink);
                }


            }
        });

        clear_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                url.setText("");
//                imageResult.setImageBitmap(null);
                    call_question_api(1);
            }
        });

        call_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layhinh();
            }
        });




    }

    private void call_question_api(int id)
    {
        ApiService.apiService.get_question(id).enqueue(new Callback<question>() {
            @Override
            public void onResponse(Call<question> call, Response<question> response) {
                Toast.makeText(test_api.this,"call api successful",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<question> call, Throwable t) {
                Toast.makeText(test_api.this,"call api Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void clickCallApi(String id) {

        ApiService.apiService.get_user(id).enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                Toast.makeText(test_api.this,"call api Unsuccessful",Toast.LENGTH_SHORT).show();
                temp = response.body();
                int test = temp.point;
                String text = String.valueOf(test);
                a.setText(text);
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(test_api.this,"call api Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });
    }





    private void call_api_update_point(String User_ID,user account)
    {
        ApiService.apiService.updatePoint(User_ID,account).enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                Toast.makeText(test_api.this,"call api Successful",Toast.LENGTH_SHORT).show();
                user test = response.body();
                String point = String.valueOf(test.point);
                a.setText(point);
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(test_api.this,"call api Unsuccessful",Toast.LENGTH_SHORT).show();
            }
        });
    }




    private class LoadImage extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView imageResult)
        {
            this.imageView = imageResult;
        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            String  urllink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urllink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageResult.setImageBitmap(bitmap);
        }
    }


    private void layhinh()
    {
        ApiService.apiService.lay_url(1).enqueue(new Callback<IMG>() {
            @Override
            public void onResponse(Call<IMG> call, Response<IMG> response) {
                Toast.makeText(test_api.this,"call api Successful",Toast.LENGTH_SHORT).show();
                IMG x = response.body();
                a.setText(x.img_url.toString());

            }

            @Override
            public void onFailure(Call<IMG> call, Throwable t) {
                Toast.makeText(test_api.this,"call api Unsuccessful",Toast.LENGTH_SHORT).show();

            }
        });
    }


}