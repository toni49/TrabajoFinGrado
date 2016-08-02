package com.example.antonio.puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by antonio on 8/1/16.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    Button login, newsignup;
    EditText username, password;
    UserLocalStore userLocalStore;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.user_input);
        password = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.buttonLogin);
        newsignup = (Button) findViewById(R.id.newuser);

        login.setOnClickListener(this);
        newsignup.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.buttonLogin:

                User user = new User(null, null);

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);


                break;

            case R.id.newuser:
                startActivity(new Intent(this, SignUp.class));      //Boton crear nuevo usuario, pasamos a la clase signup.

                break;
        }

    }
}
