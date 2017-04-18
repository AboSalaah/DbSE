package com.example.ahmedsaleh.dbse;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UniFragment extends Fragment {
    Exp_list_Adapter myAdapter;
    ExpandableListView expandableListView;
    String result=null;
    String URL="http://9cbcd085.ngrok.io/dbse/public/api/v1/university";
    public UniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_uni2, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //this part will be deleted
        expandableListView=(ExpandableListView)getView().findViewById(R.id.exp_listview_uni);
        String header_items[]=getResources().getStringArray(R.array.Universities);
        String cairo[]=getResources().getStringArray(R.array.h1);
        String alex[]=getResources().getStringArray(R.array.h2);
        String t2fel[]=getResources().getStringArray(R.array.h3);
        String ain[]=getResources().getStringArray(R.array.h4);
        String helwan[]=getResources().getStringArray(R.array.h5);
        ArrayList<String> l1=new ArrayList<>();
        ArrayList<String>l2=new ArrayList<>();
        ArrayList<String>l3=new ArrayList<>();
        ArrayList<String>l4=new ArrayList<>();
        ArrayList<String>l5=new ArrayList<>();
        ArrayList<String>headings=new ArrayList<>();
        HashMap<String,ArrayList<String>> childs=new HashMap<>();
        for(String title:header_items)
        {
            headings.add(title);
        }
        for(String child:cairo)l1.add(child);
        for(String child:alex)l2.add(child);
        for(String child:t2fel)l3.add(child);
        for(String child:ain)l4.add(child);
        for(String child:helwan)l5.add(child);
        childs.put(headings.get(0),l1);
        childs.put(headings.get(1),l2);
        childs.put(headings.get(2),l3);
        childs.put(headings.get(3),l4);
        childs.put(headings.get(4),l5);
         myAdapter=new Exp_list_Adapter(getActivity(),headings,childs);
        expandableListView.setAdapter(myAdapter);
       // connect();
    }



    /*void connect()
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException
            {
                result=response.body().string().toString();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {

                        ArrayList<String>headers=new ArrayList<String>();
                        ArrayList<String>childs=new ArrayList<String>();

                        HashMap<String,ArrayList<String>>hashMap=new HashMap<String, ArrayList<String>>();
                        try {
                           JSONArray jsonArray=new JSONArray(result);
                           for(int i=0;i<jsonArray.length();++i)
                           {
                               JSONObject jsonObject=jsonArray.getJSONObject(i);
                              headers.add(jsonObject.getString("name"));
                               JSONArray jsonArray1=jsonObject.getJSONArray("faculties");
                               for(int j=0;j<jsonArray1.length();++j)
                               {
                                   childs.add(jsonArray1.getString(i));
                               }
                               hashMap.put(headers.get(i),new ArrayList<String>(childs));
                               childs.clear();

                           }

                            myAdapter.setAdapter(getActivity(),headers,hashMap);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });

    }
    */
}


