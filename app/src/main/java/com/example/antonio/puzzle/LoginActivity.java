package com.example.antonio.puzzle;

/**
 * Created by antonio on 9/12/16.
 */
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText userInput = (EditText) findViewById(R.id.user_input);
        final EditText passInput = (EditText) findViewById(R.id.password_input);
        final Button tvRegisterLink = (Button) findViewById(R.id.newuser);
        final Button bLogin = (Button) findViewById(R.id.buttonLogin);
        final Button bInvitado = (Button) findViewById(R.id.buttonAn);

        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);


        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.start();
                Intent registerIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = userInput.getText().toString().trim();
                final String password = passInput.getText().toString().trim();

                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {

                                    userInput.setText("");  //Borramos el nombre de usuario de la vista
                                    passInput.setText("");  //Borramos la contraseña de la vista
                                    Level datos = new Level();
                                    datos.setUsername(username);
                                    datos.setPassword(password);
                                    // String name = jsonResponse.getString("name");
                                    // int age = jsonResponse.getInt("age");
                                    click.start();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    // intent.putExtra("name", name);
                                    // intent.putExtra("age", age);
                                    intent.putExtra("username", username);
                                    LoginActivity.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Los datos no son validos")
                                            .setNegativeButton("Volver a intentar", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }

        });
    }
}
