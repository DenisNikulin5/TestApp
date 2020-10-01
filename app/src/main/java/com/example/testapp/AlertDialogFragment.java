package com.example.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {
    private String alertMessage;

    public void SetAlertMessage(String message)
    {
        this.alertMessage = message;
    }

    public String GetAlertMessage()
    {
        return this.alertMessage;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(this.alertMessage)
                .setTitle("Alert")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                }});
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
