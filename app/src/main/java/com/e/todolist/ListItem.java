package com.e.todolist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.List_item;

public class ListItem extends ArrayAdapter<List_item> {
    private Context context;
    private List<List_item> list;
    // constructor
    public ListItem(Context context, int resource, ArrayList<List_item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        final List_item property = list.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_widget, null);
        final TextView Title = (TextView) view.findViewById(R.id.titleTextView);
        TextView Subtitle = (TextView) view.findViewById(R.id.subTitleTextView);
        final ImageView checkBox = (ImageView) view.findViewById(R.id.done);
        ImageView reminder = (ImageView) view.findViewById(R.id.reminder);
        ImageView checklist = (ImageView) view.findViewById(R.id.checklist);
        initialize(property, checkBox, Title, view, reminder, checklist);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("checked", String.valueOf(property.isChecked()));
                if(!property.isChecked()){
                    Title.setPaintFlags(Title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    checkBox.setImageResource(R.drawable.ic_verified);
                    property.setChecked(true);
                }
                else{
                    Title.setPaintFlags(Title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    checkBox.setImageResource(R.drawable.ic_circle);
                    property.setChecked(false);
                }
            }
        });

        //set Title and Subtitle
        String title = " " + property.getTitle();
        Title.setText(title);

        String subtitle = " "+ property.getSubtitle();
        Subtitle.setText(subtitle);

        return view;
    }

    public void initialize(List_item property, ImageView checkBox, TextView Title, View view, ImageView reminder, ImageView checklist){
        Title = (TextView) view.findViewById(R.id.titleTextView);
        checkBox = (ImageView) view.findViewById(R.id.done);

        if(property.isChecklist()){
            checklist.setVisibility(View.VISIBLE);
        }
        else{
            checklist.setVisibility(View.INVISIBLE);
        }
        if(property.isReminder()){
            reminder.setVisibility(View.VISIBLE);
        }
        else{
            reminder.setVisibility(View.INVISIBLE);
        }
        if(property.isChecked()){
            Title.setPaintFlags(Title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkBox.setImageResource(R.drawable.ic_verified);
            property.setChecked(false);

        }
        else{
            Title.setPaintFlags(Title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            checkBox.setImageResource(R.drawable.ic_circle);
            property.setChecked(true);

        }
    }
}
