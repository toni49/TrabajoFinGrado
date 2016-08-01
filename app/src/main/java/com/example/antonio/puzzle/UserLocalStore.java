package com.example.antonio.puzzle;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by antonio on 8/1/16.
 */
public class UserLocalStore {

    public static final String SP_NAME ="userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("nombre", user.username);
        spEditor.putInt("edad", user.age);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getLoggedInUser() {

        String nombre = userLocalDatabase.getString("nombre", "");
        String password = userLocalDatabase.getString("contraseña", "");
        int edad = userLocalDatabase.getInt("edad", -1);

        User storedUser = new User(nombre, password, edad);

        return storedUser;

    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();

    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false) ==  true){
            return true;
        }
        else
            return false;
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
