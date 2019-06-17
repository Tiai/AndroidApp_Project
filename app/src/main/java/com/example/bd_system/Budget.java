package com.example.bd_system;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Budget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DateSelect();
        newFragment.show(getSupportFragmentManager(),"datePicker");


    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        TextView dateText = findViewById(R.id.text_deadline);
        String date = year_string + "/" + month_string + "/" + day_string;
        dateText.setText(date);
    }

    public void addBudgetList(View view) {

    }
}
