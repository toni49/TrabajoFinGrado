package com.example.antonio.puzzle;

import android.content.Intent;
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
    EditText username, password;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = (EditText) findViewById(R.id.user_sign);
        password = (EditText) findViewById(R.id.password_sign);
        signup = (Button) findViewById(R.id.button_sign);

        signup.setOnClickListener(this);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_sign:

                String nombreusuario = username.getText().toString();
                String contrasena = password.getText().toString();

                User user = new User(nombreusuario, contrasena);

                SignUpUser(user);
                break;
        }

    }

    private void SignUpUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser){
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });
    }
}
