package com.e.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

import model.List_item;

public class MainActivity extends AppCompatActivity {

    private String textForTextView="";
    private static ArrayList<List_item> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        list.clear();
        list.add(new List_item("Title 1", "this is subtitle 1", false));
        list.add(new List_item("Title 2", "this is subtitle 2", true));
        TextView textView = (TextView) findViewById(R.id.textView);
        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<List_item> adapter = new ListItem(MainActivity.this, 0, list);
        listView.setAdapter(adapter);
        if(getIntent()!=null && getIntent().getExtras()!=null){
            if(getIntent().getExtras().containsKey("text") ){
                textForTextView = getIntent().getExtras().getString("text","");
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
        if(!textForTextView.equals("")){
            textView.setText(textForTextView);
        }

    }
}