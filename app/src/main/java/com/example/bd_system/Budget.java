package com.example.bd_system;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Budget extends AppCompatActivity {

    private int budget;
    private int remain_days;
    private int dailyCost;

    static final String STATE_BUDGET = "budget";
    private SharedPreferences sp_budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        sp_budget = getSharedPreferences(
                "shared_budget", MODE_PRIVATE);

        dailyCost = sp_budget.getInt(STATE_BUDGET, 0);

        if(dailyCost!=0){
            Toast.makeText(Budget.this, "Your daily quota is " + String.valueOf(dailyCost),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_BUDGET, dailyCost);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = sp_budget.edit();
        preferencesEditor.putInt(STATE_BUDGET, dailyCost);
        preferencesEditor.apply();
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

        Calendar toDate = Calendar.getInstance();
        toDate.set(year,month,day);

        Calendar now = Calendar.getInstance();

        long days = ( toDate.getTimeInMillis() - now.getTimeInMillis() ) / (60*60*24*1000);

        remain_days = (int)days;
    }

    public void setNotify(View view) {
        EditText editText_budget = findViewById(R.id.editText_budget);
        budget = Integer.valueOf(editText_budget.getText().toString());

        dailyCost = budget/remain_days;

        Toast.makeText(Budget.this, "Your daily quota is " + Integer.toString(dailyCost),Toast.LENGTH_LONG).show();
    }
}
