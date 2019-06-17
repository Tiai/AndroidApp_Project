package com.example.bd_system;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class Budget extends AppCompatActivity {

    private int budget;
    private int remain_days;

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

        Calendar toDate = Calendar.getInstance();
        toDate.set(year,month,day);

        Calendar now = Calendar.getInstance();

        long days = ( toDate.getTimeInMillis() - now.getTimeInMillis() ) / (60*60*24*1000);

        remain_days = (int)days;
    }

    public void setNotify(View view) {
        EditText edittext_budget = findViewById(R.id.editText_budget);
        budget = Integer.valueOf(edittext_budget.getText().toString());

        int dailycost = budget/remain_days;
    }
}
