package com.example.bd_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
