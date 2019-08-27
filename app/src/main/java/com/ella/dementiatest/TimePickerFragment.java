package com.ella.dementiatest;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener mListener;
    private Context context;


    public TimePickerFragment() {
        // Required empty public constructor
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(context, mListener, hour, minute, DateFormat.is24HourFormat(context));
    }

}
