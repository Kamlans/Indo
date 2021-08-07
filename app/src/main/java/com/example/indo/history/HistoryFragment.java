package com.example.indo.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.indo.MainActivity;
import com.example.indo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HistoryFragment extends Fragment {

    private RequestQueue requestQueue;
    private String token , email;
    private static final String TAG = "kamlans" ;
    private LineChart riskIndex, insulin , CHO , BG;
    private SharedPreferences sharedPreferences;
    List<Entry> insulinList = new ArrayList<>();
    List<Entry> riskIndexList= new ArrayList<>();
    List<Entry> CHOList = new ArrayList<>();
    List<Entry> BGList = new ArrayList<>();


    public HistoryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        sharedPreferences = getContext().getSharedPreferences("indo" , Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "def value");
        email = sharedPreferences.getString("email", "def value");

        try {
            getValue("http://34.123.33.199/api/report/report/list");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        riskIndex = view.findViewById(R.id.riskIndex_graph);
        insulin = view.findViewById(R.id.insulin_graph);
        CHO = view.findViewById(R.id.CHO_graph);
        BG = view.findViewById(R.id.BG_graph);

        for (int i = 0; i <20 ; i++) {


            try{
//                int x = 5;

//                int y1 = new Random().nextInt(10);
//                int x1 = new Random().nextInt(10);
//                int y2 = new Random().nextInt(10);
//                int x2 = new Random().nextInt(10);
//                int y3 = new Random().nextInt(10);
//                int x3 = new Random().nextInt(10);
//                int y4 = new Random().nextInt(10);
//                int x4 = new Random().nextInt(10);
//
//
//
//                Log.d(TAG, "onCreateView: "+y1 +" "+y2);
//
//                riskIndexList.add(new Entry(x1, y1));
//                insulinList.add(new Entry(x2 , y2));
//                CHOList.add(new Entry(x3 , y3));
//                BGList.add(new Entry(x4 , y4));

                riskIndexList.add(new Entry(1, 1));
                insulinList.add(new Entry(2 , 2));
                CHOList.add(new Entry(3 , 3));
                BGList.add(new Entry(4 , 4));
            }
          catch (Exception e){
              Log.d("err", "onCreateView: error is      "+e);
          }




        }








        LineDataSet riskindexDataset = new LineDataSet(riskIndexList,"risk index");
        LineDataSet insulinDataSet = new LineDataSet(insulinList,"insulin");
        LineDataSet CHO_dataset = new LineDataSet(CHOList,"CHO");
        LineDataSet BG_dataset = new LineDataSet(BGList,"BG");




        insulinDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        riskindexDataset.setColors(ColorTemplate.JOYFUL_COLORS);
        CHO_dataset.setColors(ColorTemplate.JOYFUL_COLORS);
        BG_dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        LineData insulinLineData = new LineData(insulinDataSet);
        LineData riskIndexLineData = new LineData(riskindexDataset);
        LineData CHO_LineData = new LineData(CHO_dataset);
        LineData BG_LineData = new LineData(BG_dataset);


        try {
            riskIndex.setData(riskIndexLineData);
            insulin.setData(insulinLineData);
            CHO.setData(CHO_LineData);
            BG.setData(BG_LineData);
        }
        catch (Exception e2) {
            Log.d("err", "onCreateView: "+e2);
        }

        return view;

    }

    private void getValue(String url) throws JSONException {


        requestQueue = Volley.newRequestQueue(getContext());

        Map<String  , String > parameters = new HashMap<>();
        parameters.put("email","kamlans28@gmail.com");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email" , "kamlans28@gmail.com");
       // Log.d(TAG, "getValue: "+jsonObject.toString());


        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST , url , jsonObject ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                       // Log.d(TAG, "onResponse: "+response.toString());
                        Toast.makeText(getContext() , response.toString() , Toast.LENGTH_SHORT).show();

                        JSONArray  jsonArray = new JSONArray();
                        try {
                            jsonArray = response.getJSONArray("reports");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for ( int i = 0 ; i <= jsonArray.length() ; i++){


                            try {

                                JSONObject individualValue = jsonArray.getJSONObject(i);
                               // Log.d(TAG, "onResponse: jsonobject    :"+individualValue.toString());
                                Log.d(TAG, "onResponse: -----------------------------------------------------------------------------------------------------------------");
                                Log.d(TAG, "glucose_level: "+individualValue.getString("glucose_level"));
                                Log.d(TAG, "cho_level: "+individualValue.getString("cho_level"));
                                Log.d(TAG, "insuline_level: "+individualValue.getString("insuline_level"));
                                Log.d(TAG, "insuline_dose_time: "+individualValue.getString("insuline_dose_time"));
                                Log.d(TAG, "date_time: "+individualValue.getString("date_time"));
                                Log.d(TAG, "insuline_dose_time: "+individualValue.getString("insuline_dose_time"));
                                Log.d(TAG, "physical_activity_level: "+individualValue.getString("physical_activity_level"));
                                Log.d(TAG, "patient: "+individualValue.getString("patient"));
                                Log.d(TAG, "onResponse: -----------------------------------------------------------------------------------------------------------------");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


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
}