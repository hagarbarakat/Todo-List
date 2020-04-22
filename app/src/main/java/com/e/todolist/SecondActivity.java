package com.e.todolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

import model.CheckList;
import model.List_item;

/**
 * Created by tashariko on 26/08/17.
 */

public class SecondActivity extends AppCompatActivity {

    private TextView titleTextView, subTitleTextView;
    private String titleString, subTitleString;
    private ImageButton imageButton;
    private Button button;
    private EditText title;
    private ListView listView;
    private ImageView down;
    private boolean vis = true;
    private static ArrayList<CheckList> list = new ArrayList<>();
    private ConstraintLayout constraintLayout;
    private ConstraintLayout par;
    private CheckBox reminder;
    CheckBox checkList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        list.clear();
        list.add(new CheckList("Title 1"));
        list.add(new CheckList("Title 2"));
        final ArrayAdapter<CheckList> adapter = new CheckListView(SecondActivity.this, 0, list);
        listView.setAdapter(adapter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(title == null || title.getText().toString().equals("")){
                    Toast.makeText(SecondActivity.this,"Title is Empty", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vis){
                    constraintLayout.setVisibility(View.VISIBLE);
                    vis = false;
                }
                else{
                    constraintLayout.setVisibility(View.INVISIBLE);
                    vis = true;
                }
            }
        });
        checkList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkList.isChecked()){
                    par.setVisibility(View.VISIBLE);
                }
                else{
                    par.setVisibility(View.INVISIBLE);
                }
            }
        });
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel(myCalendar);
            }

        };
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reminder.isChecked()) {
                    Intent intent = new Intent(SecondActivity.this, Date_Time.class);
                    Log.e("intent", "Date&time");
                    startActivity(intent);
                }
            }
        });
        if(getIntent()!=null && getIntent().getExtras()!=null){
            if(getIntent().getExtras().containsKey("ItemTitle")){
                titleString = getIntent().getExtras().getString("ItemTitle");
                subTitleString = getIntent().getExtras().getString("ItemSubTitle");

                titleTextView.setText(titleString);
                subTitleTextView.setText(subTitleString);
            }
        }
    }

    public void initialize(){
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        subTitleTextView = (TextView) findViewById(R.id.subTitleTextView);
        button = (Button)findViewById(R.id.button);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        title = (EditText) findViewById(R.id.editText);
        listView = (ListView)findViewById(R.id.checklistview);
        down = (ImageView) findViewById(R.id.down);
        constraintLayout = (ConstraintLayout) findViewById(R.id.cons);
        constraintLayout.setVisibility(View.INVISIBLE);
        checkList = (CheckBox) findViewById(R.id.checkBox2);
        par = (ConstraintLayout) findViewById(R.id.par);
        if(checkList.isChecked()){
            par.setVisibility(View.VISIBLE);
        }
        else{
            par.setVisibility(View.INVISIBLE);
        }
        reminder = (CheckBox) findViewById(R.id.checkBox);

    }
}