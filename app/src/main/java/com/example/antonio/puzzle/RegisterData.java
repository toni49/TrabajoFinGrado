package com.example.antonio.puzzle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by antonio on 11/5/16.
 */

public class RegisterData extends StringRequest {
    private static final String DATA_REGISTER_URL = "http://www.antoniosanz.comeze.com/Data.php";
    private Map<String, String> vars;

    public RegisterData(float maxVeloX, float maxVeloY, int fallos, float diffTime, Response.Listener<String> listener) {
        super(Request.Method.POST, DATA_REGISTER_URL, listener, null);
        //vars = new HashMap<>();
        //params.put("name", name);
        //params.put("age", age + "");
        vars = new HashMap<>();

        vars.put("maxVeloX", maxVeloX + "");
        vars.put("maxVeloY", maxVeloY + "");
        vars.put("fallos", fallos + "");
        vars.put("diffTime", diffTime + "");


    }

    @Override
    public Map<String, String> getParams() {
        return vars;
    }
}
