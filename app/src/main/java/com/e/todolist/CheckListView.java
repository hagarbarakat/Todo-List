package com.e.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.CheckList;


public class CheckListView extends ArrayAdapter<CheckList> {
    private Context context;
    private List<CheckList> list;
    // constructor
    public CheckListView(Context context, int resource, ArrayList<CheckList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        final CheckList property = list.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.checklist, null);
        final AutoCompleteTextView Title = (AutoCompleteTextView) view.findViewById(R.id.checktitle);
        LinearLayout parent1 = (LinearLayout) view.findViewById(R.id.parent);
        String title = " " + property.getTitle();
        Title.setText(title);
        Title.setEnabled(false);
        property.setEdit(true);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(property.isEdit()) {
                    Title.setEnabled(true);
                    property.setEdit(false);
                    Title.setOnKeyListener(new View.OnKeyListener() {
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            // If the event is a key-down event on the "enter" button
                            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                // Perform action on key press
                                Title.setEnabled(false);

                                return true;
                            }
                            return false;
                        }
                    });
                }
                else {
                    Title.setEnabled(false);
                    property.setEdit(true);
                }
                return false;
            }

        });
        return view;
    }
}
