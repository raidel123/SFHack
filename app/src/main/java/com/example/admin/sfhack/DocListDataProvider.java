package com.example.admin.sfhack;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Mars on 6/14/2017.
 */

public class DocListDataProvider {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SignatureRequiredList = "SignatureRequiredList";
    public static final String DocumentPendingList = "DocumentPendingList";
    public static final String DocumentCompletedList = "DocumentCompletedList";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public HashMap<String, List<String>> getData(Context context) {

        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        Gson gson = new Gson();

        String requiredDocument = sharedpreferences.getString(SignatureRequiredList , "");
        String pendingDocument = sharedpreferences.getString(DocumentPendingList , "");
        String completedDocument = sharedpreferences.getString(DocumentCompletedList , "");

        ArrayList<DocumentInfo> requiredDocumentList = gson.fromJson(requiredDocument, new TypeToken<List<DocumentInfo>>(){}.getType());
        ArrayList<DocumentInfo> pendingDocumentList = gson.fromJson(pendingDocument, new TypeToken<List<DocumentInfo>>(){}.getType());
        ArrayList<DocumentInfo> completedDocumentList = gson.fromJson(completedDocument, new TypeToken<List<DocumentInfo>>(){}.getType());

        //itemsList.add(new DocumentInfo(title, link));

        //String json = gson.toJson(new ArrayList<DocumentInfo>());
        //editor.putString(DocumentCompletedList, json);

        HashMap<String, List<String>> listDetail = new LinkedHashMap<>();

        List<String> completed = new ArrayList<String>();
        for(int i = 0; i < completedDocumentList.size(); i++){
            completed.add(completedDocumentList.get(i).getDocTitle());
        }

//        completed.add("doc 1");
//        completed.add("doc 2");
//        completed.add("doc 3");
//        completed.add("doc 4");
//        completed.add("doc 5");

        List<String> signatureRequired = new ArrayList<String>();

        for(int i = 0; i < requiredDocumentList.size(); i++){
            signatureRequired.add(requiredDocumentList.get(i).getDocTitle());
        }
//        signatureRequired.add(response);
//        signatureRequired.add(response2);
//        signatureRequired.add("doc 2");
//        signatureRequired.add("doc 3");
//        signatureRequired.add("doc 4");
//        signatureRequired.add("doc 5");

        List<String> pendingAgentAction = new ArrayList<String>();
        for(int i = 0; i < pendingDocumentList.size(); i++){
            pendingAgentAction.add(pendingDocumentList.get(i).getDocTitle());
        }

//        pendingAgentAction.add("doc 1");
//        pendingAgentAction.add("doc 2");
//        pendingAgentAction.add("doc 3");
//        pendingAgentAction.add("doc 4");
//        pendingAgentAction.add("doc 5");

        List<String> expiring = new ArrayList<String>();

//        listDetail.put("CRICKET TEAMS", cricket);
//        listDetail.put("FOOTBALL TEAMS", football);
//        listDetail.put("BASKETBALL TEAMS", basketball);
        listDetail.put("SIGNATURE REQUIRED", signatureRequired);
        listDetail.put("PENDING AGENT ACTION", pendingAgentAction);
        listDetail.put("COMPLETED", completed);
        //listDetail.put("EXPIRING", expiring);
        return listDetail;

    };
}
