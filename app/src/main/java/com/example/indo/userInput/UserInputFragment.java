package com.example.indo.userInput;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.indo.R;
import com.example.indo.UserInputDialogFragment;


public class UserInputFragment extends Fragment {

 public UserInputFragment(){

 }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_user_input, container, false);

//        Spinner spinner = view.findViewById(R.id.physicalActivitySpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.activityLevel, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        //spinner.setOnItemSelectedListener(this);

view.findViewById(R.id.updateValuesBtn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        UserInputDialogFragment userInputDialogFragment = new UserInputDialogFragment(getContext());

        userInputDialogFragment.show(getChildFragmentManager() , null);


    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }


});

        return view;
    }
}