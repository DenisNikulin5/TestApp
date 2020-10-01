package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.test_app.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCenter.start(getApplication(), "a968113b-88ae-4fa4-820e-b08b86d1e762",
                Analytics.class, Crashes.class);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        CheckBox alertCheckbox = (CheckBox) findViewById(R.id.alert_checkbox);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        if (alertCheckbox.isChecked())
        {
            AlertDialogFragment dialog = new AlertDialogFragment();
            dialog.SetAlertMessage(message);
            dialog.show(getSupportFragmentManager(), "AlertDialogFragment");
            return;
        }

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void resetControls(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        CheckBox alertCheckbox = (CheckBox) findViewById(R.id.alert_checkbox);

        editText.setText("");
        alertCheckbox.setChecked(false);
    }
}