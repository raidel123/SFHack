package com.example.admin.sfhack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SignatureRequiredList = "SignatureRequiredList";
    public static final String DocumentPendingList = "DocumentPendingList";
    public static final String DocumentCompletedList = "DocumentCompletedList";
    public static final String Test = "Test";


    SharedPreferences sharedpreferences;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listButtons);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        InitSharedPreferences();

        List<PocketAgentMenu>pocketAgentMenuList = new ArrayList<PocketAgentMenu>();

        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.folder), "Accounts & Policies"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.bills), "Bills & Payments"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.assignment), "Claims Center"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.bank), "Make a Deposit"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.document), "Document Hub"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.atm), "ATM Locator"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.car), "Roadside Assistance"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.quote), "Get a Quote"));
        pocketAgentMenuList.add(new PocketAgentMenu(BitmapFactory.decodeResource(getResources(), R.mipmap.cart), "What We Offer"));

        PocketAgentMenuAdapter adapter = new PocketAgentMenuAdapter(this, pocketAgentMenuList);

        Intent i = new Intent(this, RegistrationService.class);
        startService(i);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4){
                    Intent intent = new Intent(getApplicationContext(), DocListActivity.class);
                    startActivity(intent);
                }
            }
        });

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

        if(!sharedpreferences.contains(Test))
        {

            //String json = gson.toJson(new ArrayList<DocumentInfo>());
            editor.putString(Test, "doc1Test");

        }

        editor.commit();
    }


}
