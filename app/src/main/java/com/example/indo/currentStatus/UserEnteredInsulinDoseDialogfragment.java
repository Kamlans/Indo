package com.example.indo.currentStatus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.indo.R;
import com.example.indo.UserInputDialogFragment;

public class UserEnteredInsulinDoseDialogfragment extends DialogFragment {

    private EditText enteredInsulinDoseEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.user_entering_insulin_value_layout , null);

        enteredInsulinDoseEditText = view.findViewById(R.id.userEnteredInsulinDoseInDialogEditText);


        builder.setView(view)
                // Add action buttons
                .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "entered value is : " +enteredInsulinDoseEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UserEnteredInsulinDoseDialogfragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
