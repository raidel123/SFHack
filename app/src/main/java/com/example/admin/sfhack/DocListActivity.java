package com.example.admin.sfhack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocListActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doc_list);
//    }

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    DocListDataProvider listProvider;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SignatureRequiredList = "SignatureRequiredList";
    public static final String DocumentPendingList = "DocumentPendingList";
    public static final String DocumentCompletedList = "DocumentCompletedList";
    public static final String Test = "Test";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    protected ExpandableListView getExpandableListView() {
        return expandableListView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_list);

        listProvider = new DocListDataProvider();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = listProvider.getData(getApplicationContext());
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new DocListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                List<DocumentInfo> requiredTempList;
                List<DocumentInfo> pendingTempList;
                List<DocumentInfo> completedTempList;
                Gson gson = new Gson();
                String url;


                if (groupPosition == 0) {
                    requiredTempList = listProvider.getRequiredDocumentList();
                    url = requiredTempList.get(childPosition).getDocUrl();

                    List<DocumentInfo> tempArray2 = listProvider.getPendingDocumentList();

                    DocumentInfo tempDocInfo = requiredTempList.remove(childPosition);
                    tempArray2.add(tempDocInfo);

                    String requiredJson = gson.toJson(requiredTempList);
                    String pendingJson = gson.toJson(tempArray2);
                    editor.putString(SignatureRequiredList, requiredJson);
                    editor.putString(DocumentPendingList, pendingJson);

                    editor.apply();
                } else if (groupPosition == 1) {
                    pendingTempList = listProvider.getPendingDocumentList();
                    url = pendingTempList.get(childPosition).getDocUrl();

//                    List<DocumentInfo> tempArray2 = listProvider.getCompletedDocumentList();
//
//                    DocumentInfo tempDocInfo = tempArray.remove(childPosition);
//                    tempArray2.add(tempDocInfo);
                } else {
                    completedTempList = listProvider.getCompletedDocumentList();
                    url = completedTempList.get(childPosition).getDocUrl();

//                    List<DocumentInfo> tempArray2 = listProvider.getPendingDocumentList();
//                    if(tempArray2.indexOf(completedTempList.get(childPosition)) != -1)
//                        tempArray2.remove(tempArray2.indexOf(completedTempList.get(childPosition)));
//
//                    String pendingJson = gson.toJson(tempArray2);
//                    editor.putString(DocumentPendingList, pendingJson);
//                    editor.apply();

                    //DocumentInfo tempDocInfo = tempArray2.remove(tempArray2.indexOf(tempArray.get(childPosition)));
                    //tempArray.add(tempDocInfo);
                }

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), DocWebActivity.class);
                intent.putExtra("url", url);
                System.out.println("***** doc list url: " + url);
                startActivity(intent);
//
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
