package com.example.indo.levels;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class LevelFragment extends Fragment {

    private static final String TAG = "kamlans" ;
    private LineChart riskIndex, insulin , CHO , BG;
    List<Entry> insulinList = new ArrayList<>();
    List<Entry> riskIndexList= new ArrayList<>();
    List<Entry> CHOList = new ArrayList<>();
    List<Entry> BGList = new ArrayList<>();


    public LevelFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_level, container, false);

        riskIndex = view.findViewById(R.id.riskIndex_graph);
        insulin = view.findViewById(R.id.insulin_graph);
        CHO = view.findViewById(R.id.CHO_graph);
        BG = view.findViewById(R.id.BG_graph);

        for (int i = 0; i <20 ; i++) {


            try{
//                int x = 5;

                int y1 = new Random().nextInt(10);
                int x1 = new Random().nextInt(10);
                int y2 = new Random().nextInt(10);
                int x2 = new Random().nextInt(10);
                int y3 = new Random().nextInt(10);
                int x3 = new Random().nextInt(10);
                int y4 = new Random().nextInt(10);
                int x4 = new Random().nextInt(10);

//                int  avg1 = 0 , avg2 = 0 , avg3 = 0  , avg4 = 0 ;
//
//                 avg1 = avg1 + y1;
//                 avg2 = avg2 + y2;
//                 avg3 = avg3 + y3;
//                 avg4 = avg4 + y4;

                Log.d(TAG, "onCreateView: "+y1 +" "+y2);

                riskIndexList.add(new Entry(x1, y1));
                insulinList.add(new Entry(x2 , y2));
                CHOList.add(new Entry(x3 , y3));
                BGList.add(new Entry(x4 , y4));


            }
          catch (Exception e){
              Log.d("err", "onCreateView: error is      "+e);
          }




        }

        Log.d("risk", "onCreateView: "+riskIndexList.toString());
        Log.d("insulin", "onCreateView: "+insulinList.toString());
        Log.d("cho", "onCreateView: "+CHOList.toString());
        Log.d("bg", "onCreateView: "+BGList.toString());

        LineDataSet riskindexDataset = new LineDataSet(riskIndexList,"risk index");
        LineDataSet insulinDataSet = new LineDataSet(insulinList,"insulin");
        LineDataSet CHO_dataset = new LineDataSet(CHOList,"CHO");
        LineDataSet BG_dataset = new LineDataSet(BGList,"BG");


        Log.d(TAG, "onCreateView: -------------------------------------------------------------------------------------------------------------------");

        Log.d("risk", "onCreateView: "+riskindexDataset.toString());
        Log.d("insulin", "onCreateView: "+insulinDataSet.toString());
        Log.d("cho", "onCreateView: "+CHO_dataset.toString());
        Log.d("bg", "onCreateView: "+BG_dataset.toString());

        Log.d(TAG, "onCreateView: ************************************************************************************************8");

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
}