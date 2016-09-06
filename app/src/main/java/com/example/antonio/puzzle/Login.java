package com.example.antonio.puzzle;

import android.app.AlertDialog;
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
    EditText Username, Password;
    UserLocalStore userLocalStore;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Username = (EditText) findViewById(R.id.user_input);
        Password = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.buttonLogin);
        newsignup = (Button) findViewById(R.id.newuser);

        login.setOnClickListener(this);
        newsignup.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.buttonLogin:

                String username = Username.getText().toString();
                String password = Password.getText().toString();

                User user = new User(username, password);
                authenticate(user);

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                break;

            case R.id.newuser:
                startActivity(new Intent(this, SignUp.class));      //Boton crear nuevo usuario, pasamos a la clase signup.

                break;
        }

    }

    private void authenticate(User user){
        ServerRequests serveRequests = new ServerRequests(this);
        serveRequests.fetchUserDataInBackground(user, new GetUserCallback() {

            @Override
            public void done(User returnedUser) {
                if(returnedUser == null) {
                    showErrorMessage();
                }
                else{
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Usuario o contraseña incorrecto");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }
}
