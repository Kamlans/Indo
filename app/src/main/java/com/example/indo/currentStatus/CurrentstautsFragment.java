package com.example.indo.currentStatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.indo.R;


public class CurrentstautsFragment extends Fragment {

    private Button suggestDosageBtn;
    private TextView bglevelTextview;

    private String dosageString = "5";

    public CurrentstautsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_status, container, false);

        bglevelTextview = view.findViewById(R.id.presentBGLevelTextView);
        suggestDosageBtn = view.findViewById(R.id.suggestDosageBtn);


        bglevelTextview.setText("Your glucose Level is \n 80 mg/dL:");

        suggestDosageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDosageDialogBox(v);
            }
        });


        return view;
    }

    void confirmDosageDialogBox(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your insulin dosage is:  " + dosageString)
                .setPositiveButton("accept ?", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "You accepted the dose of : " + dosageString, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("don't accept ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UserEnteredInsulinDoseDialogfragment us = new UserEnteredInsulinDoseDialogfragment();
                        us.show(getChildFragmentManager() , null);
                        dialog.cancel();
                    }
                });

        builder.show();
    }
}