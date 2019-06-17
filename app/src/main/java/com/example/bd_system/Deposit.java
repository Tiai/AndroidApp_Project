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

public class Deposit extends AppCompatActivity {

    private int deposit;
    private int remain_days;
    private int dailySave;

    static final String STATE_DEPOSIT = "deposit";
    private SharedPreferences sp_deposit;
    private String sharedPrefFile =
            "shared_deposit";

    public static final String EXTRA_DEPOSIT =
            "com.example.android.bd_system.Deposit.extra.deposit_save";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        sp_deposit = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        dailySave = sp_deposit.getInt(STATE_DEPOSIT, 0);

        if(dailySave!=0){
            Toast.makeText(Deposit.this, "Your must save " + String.valueOf(dailySave) + " per day",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_DEPOSIT, dailySave);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = sp_deposit.edit();
        preferencesEditor.putInt(STATE_DEPOSIT, dailySave);
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

        TextView dateText = findViewById(R.id.text_depositDeadline);
        String date = year_string + "/" + month_string + "/" + day_string;
        dateText.setText(date);

        Calendar toDate = Calendar.getInstance();
        toDate.set(year,month,day);

        Calendar now = Calendar.getInstance();

        long days = ( toDate.getTimeInMillis() - now.getTimeInMillis() ) / (60*60*24*1000);

        remain_days = (int)days;
    }

    public void setNotify(View view) {
        EditText editText_budget = findViewById(R.id.editText_deposit);
        deposit = Integer.valueOf(editText_budget.getText().toString());

        dailySave = deposit/remain_days;

        Toast.makeText(Deposit.this, "Your must save " + String.valueOf(dailySave) + " per day",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Budget.class);
        intent.putExtra(EXTRA_DEPOSIT, Integer.toString(dailySave));
    }
}
