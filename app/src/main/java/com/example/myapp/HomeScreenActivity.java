package com.example.myapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreenActivity extends AppCompatActivity {
    public Button let_go;
    public TextView score;
    private  static final int MY_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        score = (TextView)findViewById(R.id.text_total_score);
        Intent mIntent = getIntent();
        int score_intent = mIntent.getIntExtra("score", 0);
        String user_id = mIntent.getStringExtra("user_id");
        String score_updated =String.valueOf(score_intent);
        score.setText(score_updated);
        Log.d(user_id, "Home: ");
        let_go = (Button) findViewById(R.id.button_letgo);
        let_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_new = score.getText().toString();
                Integer value_of_score = Integer.valueOf(score_new);
                Intent intent = new Intent(HomeScreenActivity.this, EnglishGameChoiceActivity.class);
                intent.putExtra("score",value_of_score);
                intent.putExtra("user_id",user_id);
                Toast.makeText(HomeScreenActivity.this,"Start to play game ",Toast.LENGTH_SHORT).show();
                startActivityForResult(intent,MY_REQUEST_CODE);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MY_REQUEST_CODE == requestCode && RESULT_OK == resultCode)
        {
            int score_update = data.getIntExtra("score", 0);

            String score_updated =String.valueOf(score_update);
            score.setText(score_updated);


        }
    }
}