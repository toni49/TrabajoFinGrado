package com.example.antonio.puzzle;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by antonio on 8/1/16.
 */
public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button signup;
    EditText username, password, age;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = (EditText) findViewById(R.id.user_sign);
        password = (EditText) findViewById(R.id.password_sign);
        age = (EditText) findViewById(R.id.date_sign);
        signup = (Button) findViewById(R.id.button_sign);

        signup.setOnClickListener(this);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_sign:

                String nombreusuario = username.getText().toString();
                String contrasena = password.getText().toString();
                int edad = Integer.parseInt(age.getText().toString());

                User registeredData = new User(nombreusuario, contrasena, edad);

                break;
        }

    }
}