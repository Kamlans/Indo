package com.example.indo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "kamlans2";
    private TextView forgotPasswordBtn;
    private EditText emailEditText , passwordEditText;
    private Button Loginbtn;
    private String token= "";
    private static String tkncpy="";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private RequestQueue requestQueue2;


    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://localhost:9090/SpringMVCExample";

    private static final String POST_URL = "http://34.123.33.199/api/report/report/list";

    private static final String POST_PARAMS = "email=kamlans28@gmail.com";





    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        requestQueue2 = Volley.newRequestQueue(getApplicationContext());

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

//                volleydataDemo();
             //   ownVolley();

//                try {
//                    sendPOST(token);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.d(TAG, "onClick: "+e);
//                }
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
            postData.put("password", "mpa$734wbsR0");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, "onResponse: "+response.toString());
                    token = response.get("token").toString();
                   editor.putString("email", email);
                    editor.putString("token" , token);
                    Log.d(TAG, "onResponse: "+token);
                    editor.apply();
                    editor.commit();

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
                Log.d(TAG, "onErrorResponse****: "+error);
            }



        });

        requestQueue.add(jsonObjectRequest);

    }

    public void volleydataDemo( ){

        Log.d(TAG, "volleyPost: "+ NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted());
        String postUrl = "http://34.123.33.199/api/report/report/list";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {

         postData.put("email", "kamlans28@gmail.com");


            Log.d(TAG, "volleydataDemo: post data ::: "+postData.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, "onResponse: "+response.toString());

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
                Log.d(TAG, "onErrorResponse:____ "+error);
            }



        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("Authorization" , "Bearer "+token);
                return  params;
            }
        };

        requestQueue2.add(jsonArrayRequest);


    }





public void ownVolley ()  {

        String url = "http://34.123.33.199/api/report/report/list";

        JSONObject jsonObject = new JSONObject();
    try {
        jsonObject.put("email","kamlans28@gmail.com");
    } catch (JSONException e) {
        e.printStackTrace();
        Log.d(TAG, "ownVolley: "+e);
    }

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "my own req"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: my own req   "+error.getMessage());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("email","kamlans28@gmail.com");
                return params;
            }

            @Override
            public byte[] getBody() {
                String value = jsonObject.toString();
                try {

                    return  value == null ? null : value.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", value, "utf-8");
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String,String>();
                headers.put("content-type","application/fesf");
                headers.put("Authorization" , "Bearer "+token);
                return headers;
            }
        };

    requestQueue2.add(jsonObjectRequest);
}








    private void sendPOST(String tkn) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);


        con.setRequestProperty("Authorization", "Bearer "+token );
        con.setRequestProperty("email", "kamlans28@gmail.com");

        // For POST only - START
//        con.setDoOutput(true);
//        OutputStream os = con.getOutputStream();
//        os.write(POST_PARAMS.getBytes());
//        os.flush();
//        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        Log.d(TAG, "sendPOST: code::::::::::::::::::::::::"+responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            Log.d(TAG, "sendPOST: "+response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
















}