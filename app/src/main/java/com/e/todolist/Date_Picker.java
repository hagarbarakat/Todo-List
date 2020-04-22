package com.e.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Date_Picker  extends Fragment {
    private DatePicker datePicker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.date_layout, container, false);
        final Calendar myCalendar = Calendar.getInstance();

        datePicker = (DatePicker) rootView.findViewById(R.id.date);
        int day = datePicker.getDayOfMonth();
        Log.e("date", String.valueOf(day));
        datePicker.init( datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        int selectedYear = year;
                        int selectedMonth = monthOfYear;
                        int selectedDayOfMonth = dayOfMonth;
                        Log.e("date", "selectedDate = " + selectedYear  + "-" + selectedMonth + "-" + selectedDayOfMonth );
                        //TODO save or use selected value here
                    }

                });
        return rootView;
    }

    public static Date_Picker newInstance(String text) {

        Date_Picker f = new Date_Picker();
        Bundle b = new Bundle();
        Bundle e =  f.getArguments();
        Log.e("date", String.valueOf(e));
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
