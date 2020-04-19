package com.e.todolist;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Created by tashariko on 26/08/17.
 */


public class ListViewWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new AppWidgetListView (this.getApplicationContext(), WidgetDataModel.getDataFromSharedPrefs(getApplicationContext()));
    }
}

class AppWidgetListView implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<WidgetDataModel> dataList;
    private Context context;

    public AppWidgetListView(Context applicationContext, ArrayList<WidgetDataModel> dataList) {
        this.context=applicationContext;
        this.dataList=dataList;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.list_item_widget);

        views.setTextViewText(R.id.titleTextView, dataList.get(position).title);
        views.setTextViewText(R.id.subTitleTextView, dataList.get(position).subTitle);
        views.setImageViewResource(R.id.imageView2, R.drawable.ic_calendar);
        Log.e("msg", String.valueOf(dataList.get(position).reminder));

        if(!dataList.get(position).reminder){
            Log.e("msg", String.valueOf(dataList.get(position).reminder));
            views.setViewVisibility(R.id.imageView2, View.INVISIBLE);
        }
        else{
            views.setViewVisibility(R.id.imageView2, View.VISIBLE);
        }
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("ItemTitle",dataList.get(position).title);
        fillInIntent.putExtra("ItemSubTitle",dataList.get(position).subTitle);
        fillInIntent.putExtra("reminder", dataList.get(position).reminder);
        views.setOnClickFillInIntent(R.id.parentView, fillInIntent);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {

        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
