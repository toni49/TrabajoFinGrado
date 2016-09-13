package com.example.antonio.puzzle;

/**
 * Created by antonio on 9/12/16.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
       // String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
       // int age = intent.getIntExtra("age", -1);

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
       // EditText etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message = username + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
       // etAge.setText(age + "");
    }
}
