package com.example.ahmedsaleh.dbse.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.ahmedsaleh.dbse.Activities.SingIn;
import com.example.ahmedsaleh.dbse.Adapters.Exp_list_Adapter;
import com.example.ahmedsaleh.dbse.Helpers.ListItem;
import com.example.ahmedsaleh.dbse.R;

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

/**
 * Fragment that shows Institues list and thier Dapartments using ExpandableListView and Exp_list_Adapter
 */
public class InstitutesFragment extends Fragment {

    private Exp_list_Adapter myAdapter;
    private ExpandableListView expandableListView;
    private String result=null;
    private StringBuilder url=new StringBuilder();
    public InstitutesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institutes2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //this part will be deleted
        expandableListView=(ExpandableListView)getView().findViewById(R.id.exp_listview_inst);
       /* String header_items[]=getResources().getStringArray(R.array.Universities);
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
         */
        ArrayList<ListItem> headers=new ArrayList<ListItem>();
        ArrayList<ListItem>childs=new ArrayList<ListItem>();
        HashMap<ListItem,ArrayList<ListItem>> hashMap=new HashMap<>();
        headers.add(new ListItem("T2feeel",1,"institute"));
        childs.add(new ListItem("handasaT2feel",1,"institute"));
        childs.add(new ListItem("tebT2feel",2,"institute"));
        hashMap.put(headers.get(0),childs);
        myAdapter=new Exp_list_Adapter(getActivity(),new ArrayList<ListItem>(headers),new HashMap<ListItem,ArrayList<ListItem>>(hashMap));
        expandableListView.setAdapter(myAdapter);
        url.setLength(0);
        url.append(getString(R.string.url)+"institute"+"?token="+ SingIn.token);
        Log.i("tag","instituteurl  "+url.toString());
        connect();


    }

    /**
     * Function to make the connection to get the desired acamdemies and update the UI
     * @author Ahmed Saleh
     * @return void
     */
    void connect()
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url.toString())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException
            {
                result=response.body().string().toString();
                Log.i(getTag(),"amiiiiiiiiiiiiirr "+result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {

                        ArrayList<ListItem> headers=new ArrayList<ListItem>();
                        ArrayList<ListItem>childs=new ArrayList<ListItem>();

                        HashMap<ListItem,ArrayList<ListItem>>hashMap=new HashMap<ListItem, ArrayList<ListItem>>();
                        try {

                            JSONArray universities=new JSONArray(result);
                            for(int i=0;i<universities.length();++i)
                            {
                                JSONObject uni=universities.getJSONObject(i);
                                String name=uni.getString("name");
                                int id=uni.getInt("id");
                                headers.add(new ListItem(name,id,"institute"));
                                String departments=uni.getString("departments");
                                String parts[]=departments.split("/");
                                for(int j=0;j<parts.length;++j)
                                {

                                    childs.add(new ListItem(parts[j],j,"instdep"));
                                }
                                hashMap.put(headers.get(i),new ArrayList<ListItem>(childs));
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





}
