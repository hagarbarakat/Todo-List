package com.e.todolist;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;


public class ExampleAppWidgetProvider  extends AppWidgetProvider {

    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Toast.makeText(context, "Add", Toast.LENGTH_LONG).show();

        /*Loop for every App Widget instance that belongs to this provider.
         Users might have multiple instances of the same
         widget on their home screen.*/

        for (int appWidgetID : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetID);
        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        if(width<300) {
            WidgetUpdateService.startActionUpdateAppWidgets(context,false);
        }else{
            WidgetUpdateService.startActionUpdateAppWidgets(context,true);
        }
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    public static void sendRefreshBroadcast(Context context) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, ExampleAppWidgetProvider.class));
        context.sendBroadcast(intent);
    }
    public static void updateAllAppWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        RemoteViews remoteView;
        if (width < 300) {
            remoteView= getViewForSmallerWidget(context);
        } else {
            remoteView= getViewForBiggerWidget(context,options);
        }
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);

    }
    private static RemoteViews getViewForBiggerWidget(Context context, Bundle options) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget_white_simple);
        int minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        if (minHeight < 100) {
            views.setViewVisibility(R.id.titleTextView, View.GONE);
        }else{
            views.setViewVisibility(R.id.titleTextView, View.VISIBLE);

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("text","Coming from the Widget title click.");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.titleTextView, pendingIntent);
        }

        Intent intent = new Intent(context, ListViewWidgetService.class);
        views.setRemoteAdapter(R.id.listView, intent);

        Intent appIntent = new Intent(context, SecondActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.listView, appPendingIntent);

        return views;
    }
    private static RemoteViews getViewForSmallerWidget(Context context) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget_simple);

        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, 0);
        views.setOnClickPendingIntent(R.id.widgetImageView, pendingIntent1);

        Intent intent2 = new Intent(context, SecondActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
        views.setOnClickPendingIntent(R.id.clickTextView, pendingIntent2);

        return views;
    }
}