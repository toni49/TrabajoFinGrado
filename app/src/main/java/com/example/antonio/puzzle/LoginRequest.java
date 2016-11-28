package com.example.antonio.puzzle;

/**
 * Created by antonio on 9/12/16.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //private static final String LOGIN_REQUEST_URL = "http://www.antoniosanz.comeze.com/FetchUserData.php";
    private static final String LOGIN_REQUEST_URL = "http://www.antoniosanz.hol.es/FetchUser.php";

    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
