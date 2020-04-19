package com.e.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.List_item;

public class ListItem extends ArrayAdapter<List_item> {
    private Context context;
    private List<List_item> list;

    public ListItem(Context context, int resource, ArrayList<List_item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        List_item property = list.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_widget, null);
        TextView Title = (TextView) view.findViewById(R.id.titleTextView);
        TextView Subtitle = (TextView) view.findViewById(R.id.subTitleTextView);

        //set Title and Subtitle
        String title = " " + property.getTitle();
        Title.setText(title);

        String subtitle = " "+ property.getSubtitle();
        Subtitle.setText(subtitle);

        return view;
    }

}
