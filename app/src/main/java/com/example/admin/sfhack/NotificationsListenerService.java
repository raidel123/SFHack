package com.example.admin.sfhack;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class NotificationsListenerService extends GcmListenerService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;

    private static final String TAG = "MyGcmListenerService";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SignatureRequiredList = "SignatureRequiredList";
    public static final String DocumentPendingList = "DocumentPendingList";
    public static final String DocumentCompletedList = "DocumentCompletedList";
    public static final String Test = "Test";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        String title = data.getString("title");
        String link = data.getString("link");
        Log.d(TAG, "Title: " + title);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Link: " + link);

        //itemsList = new ArrayList<>();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        //InitSharedPreferences();

        if(title.equals("Signature Required")){
            Gson gson = new Gson();
            String response = sharedpreferences.getString(SignatureRequiredList , "");
            ArrayList<DocumentInfo> itemsList = gson.fromJson(response,
                    new TypeToken<List<DocumentInfo>>(){}.getType());

            itemsList.add(new DocumentInfo(message, link));
            String json = gson.toJson(itemsList);

            editor.putString(SignatureRequiredList, json);
            editor.apply();

        } else if (title.equals("Document Pending")){
            Gson gson = new Gson();
            String response = sharedpreferences.getString(DocumentPendingList , "");
            ArrayList<DocumentInfo> itemsList = gson.fromJson(response,
                    new TypeToken<List<DocumentInfo>>(){}.getType());

            itemsList.add(new DocumentInfo(message, link));

            String json = gson.toJson(itemsList);
            editor.putString(DocumentPendingList, json);
            editor.apply();

        }else if (title.equals("Document Completed")){
            Gson gson = new Gson();
            String response = sharedpreferences.getString(DocumentCompletedList , "");
            ArrayList<DocumentInfo> itemsList = gson.fromJson(response,
                    new TypeToken<List<DocumentInfo>>(){}.getType());

            itemsList.add(new DocumentInfo(message, link));

            String json = gson.toJson(itemsList);
            editor.putString(DocumentCompletedList, json);
            editor.apply();

        }

        editor.commit();

        sendNotification(title,message);
    }

    private void sendNotification(String title, String body) {
        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title)
                .setContentText(body);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }

    public void InitSharedPreferences(){

        Gson gson = new Gson();
        if(!sharedpreferences.contains(SignatureRequiredList))
        {

            String json = gson.toJson(new ArrayList<DocumentInfo>());
            editor.putString(SignatureRequiredList, json);

        }

        if(!sharedpreferences.contains(DocumentPendingList))
        {
            String json = gson.toJson(new ArrayList<DocumentInfo>());
            editor.putString(DocumentPendingList, json);
        }

        if(!sharedpreferences.contains(DocumentCompletedList))
        {
            String json = gson.toJson(new ArrayList<DocumentInfo>());
            editor.putString(DocumentCompletedList, json);
        }

        editor.commit();
    }

}