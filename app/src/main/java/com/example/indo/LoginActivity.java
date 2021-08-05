package com.example.indo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "kamlans2";
    private TextView forgotPasswordBtn;
    private EditText emailEditText , passwordEditText;
    private Button Loginbtn;
    private String token= "";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText =  findViewById(R.id.edittextEmail);
        passwordEditText =  findViewById(R.id.edittextPw);

        sharedPreferences = getSharedPreferences("indo" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String pw = passwordEditText.getText().toString();
                volleyPost(email , pw);


            }
        });
    }

    public void volleyPost(String email , String pw ){

        Log.d(TAG, "volleyPost: "+ NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted());
        String postUrl = "http://34.123.33.199/api/auth/account";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", pw);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, "onResponse: "+response.toString());
                    token = response.get("token").toString();
                    editor.putString("token" , token);
                    Log.d(TAG, "onResponse: "+token);
                    editor.apply();
                    editor.commit();
                    Log.d(TAG, "onCreate: token ::"+  sharedPreferences.contains("token"));
                    Log.d(TAG, "onCreate: token value is ::::::"+sharedPreferences.getString("token", "def value"));
                    startActivity(new Intent(getApplicationContext() , MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "onResponse: error is ::::"+e);
                    Toast.makeText(getApplicationContext(), e.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d(TAG, "onErrorResponse: "+error);
            }



        });

        requestQueue.add(jsonObjectRequest);

    }
}