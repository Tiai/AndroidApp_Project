package com.example.bd_system;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import static android.support.v4.content.ContextCompat.getSystemService;

public class DateSelect extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker,
                          int year, int month, int day) {

        try {
            Budget b_activity = (Budget) getActivity();
            b_activity.processDatePickerResult(year, month, day);
        }
        catch (Exception e){
            Deposit d_activity = (Deposit) getActivity();
            d_activity.processDatePickerResult(year, month, day);
        }
    }

}
