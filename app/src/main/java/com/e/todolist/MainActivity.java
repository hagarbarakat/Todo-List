package com.e.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        list.add(new List_item("Title 1", "this is subtitle 1", false, true, true));
        list.add(new List_item("Title 2", "this is subtitle 2", true, false, false));
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
                long viewId = view.getId();
                Log.e("viewId", String.valueOf(viewId));
                if(viewId == R.id.done){
                    Toast.makeText(MainActivity.this, "button", Toast.LENGTH_LONG).show();
                    Log.e("ayea", "Sa7");
                }
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
        if(!textForTextView.equals("")){
            textView.setText(textForTextView);
        }
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub

                Toast.makeText(MainActivity.this, "Position"+ String.valueOf(position), Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }

        });

    }
}