package com.e.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Created by tashariko on 26/08/17.
 */

public class SecondActivity extends AppCompatActivity {

    private TextView titleTextView, subTitleTextView;
    private String titleString, subTitleString;
    private ImageButton imageButton;
    private Button button;
    private EditText title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();

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
    }
}