package com.example.bd_system;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int budget;
    private int remain_days;

    static final String STATE_BUGET = "budget";
    private SharedPreferences sp_budget;
    private String sharedPrefFile =
            "com.example.android.bd_system";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_budget = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        budget = sp_budget.getInt(STATE_BUGET, 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_BUGET, budget);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = sp_budget.edit();
        preferencesEditor.putInt(STATE_BUGET, budget);
        preferencesEditor.apply();
    }

    public void addBudget(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Budget.class);
        startActivity(intent);
    }

    public void addDeposit(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Deposit.class);
        startActivity(intent);
    }
}
