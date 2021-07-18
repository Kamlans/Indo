package com.example.indo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.indo.userInput.UserInputFragment;

public class UserInputDialogFragment extends DialogFragment {

    private String carb , insulin , dossagetime ;
    private EditText  carbInputEdittext , insulinInputedittext , insulindosageTimeedittext;
    private Spinner activitylevelSpinner;


    Context context;

    public UserInputDialogFragment(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Get the layout inflater
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.input_param_dialog_layout , null);

        carbInputEdittext = view.findViewById(R.id.cholevelInEditTextOfdialog);
        insulinInputedittext = view.findViewById(R.id.insulin_levelInEditTextOfdialog);
        insulindosageTimeedittext = view.findViewById(R.id.insulin_dosetime_InEditTextOfdialog);


        Intent intent = new Intent(context , UserInputFragment.class);
        intent.putExtra("carb" , carbInputEdittext.getText().toString());
        intent.putExtra("insulin" , insulinInputedittext.getText().toString());
        intent.putExtra("insulinDose" , insulindosageTimeedittext.getText().toString());

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(intent);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UserInputDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            listener = (ExampleDialogListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() +
//                    "must implement ExampleDialogListener");
//        }
//    }
//
//
//    public interface ExampleDialogListener {
//        void applyTexts(String carb, String insulin , String doseTime , String activityLevel);
//    }
}
