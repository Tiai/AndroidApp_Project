package com.example.bd_system;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    private int totalcount;

    static final String STATE_BUDGET = "budget";
    private SharedPreferences sp_budget;
    private int dailyCost;

    static final String STATE_DEPOSIT = "deposit";
    private SharedPreferences sp_deposit;
    private int dailySave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_budget = getSharedPreferences(
                "shared_budget", MODE_PRIVATE);
        dailyCost = sp_budget.getInt(STATE_BUDGET, 0);

        sp_deposit = getSharedPreferences(
                "shared_deposit", MODE_PRIVATE);
        dailySave = sp_deposit.getInt(STATE_DEPOSIT, 0);

        totalcount = dailyCost + dailySave;

        if(totalcount != 0){
           Toast.makeText(MainActivity.this, "You must have " + String.valueOf(totalcount) + " at the end of the day" ,Toast.LENGTH_LONG).show();
        }
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
