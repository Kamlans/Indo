package com.example.indo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private SharedPreferences sharedPreferences;
    private static final String TAG = "kamlans2";
    private String token =null;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        sharedPreferences = getSharedPreferences("indo" , Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "def value");


//        getValue("http://34.123.33.199/api/auth/account/get");
//        getValue("http://34.123.33.199/api/patient/profile?patient_id=1");
//        getValue("http://34.123.33.199/api/doctor/profile?doctor_id=1");
//        try {
//            getValue("http://34.123.33.199/api/report/report/list");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        getValue("http://34.123.33.199/api/patient/get");
//        getValue("http://34.123.33.199/api/patient/get");

        viewPager2.setAdapter( new FragmentAdapter( getSupportFragmentManager() , getLifecycle()));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int selectedTab = tab.getId();

                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.AboutUsMenuItem:
                startActivity( new Intent(getApplicationContext() , AboutUsActivity.class));
                break;

            case R.id.LogutBtnInMenuitem:
                startActivity(new Intent(getApplicationContext() , LoginActivity.class));
        }
        return true;
    }


    private void getValue(String url) throws JSONException {


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Map<String  , String > parameters = new HashMap<>();
        parameters.put("email","kamlans28@gmail.com");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email" , "kamlans28@gmail.com");
        Log.d(TAG, "getValue: "+jsonObject.toString());


        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST , url , jsonObject ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Log.d(TAG, "onResponse: "+response.toString());
                        Toast.makeText(MainActivity.this, response.toString() , Toast.LENGTH_SHORT).show();


                    }


                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("Authorization" , "Bearer "+token);
                params.put("Content-Type" , "application/json");
                params.put("charset" , "UTF-8");
                Log.d(TAG, "getHeaders: "+params.toString());
                return  params;
            }




        };

        requestQueue.getCache().clear();
        requestQueue.add(objectRequest);

    }

    public void volleyPost(String email , String pw ){

      //  Log.d(TAG, "volleyPost: "+ NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted());
        String postUrl = "http://34.123.33.199/api/auth/account";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", "mpa$734wbsR0");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "volleyPost: "+postData.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
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
                Log.d(TAG, "onErrorResponse****: "+error);
            }



        });

        requestQueue.add(jsonObjectRequest);

    }
}