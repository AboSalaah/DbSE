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
 * Fragment represent list of universities
 */
public class UniFragment extends Fragment {
   private Exp_list_Adapter myAdapter;
    private ExpandableListView expandableListView;
    private String result=null;
    private StringBuilder url=new StringBuilder();
    //String URL="http://516c8af0.ngrok.io/dbse/public/api/v1/university?token=";
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


        myAdapter=new Exp_list_Adapter(getActivity(),new ArrayList<ListItem>(),new HashMap<ListItem,ArrayList<ListItem>>());
        expandableListView.setAdapter(myAdapter);
        url.setLength(0);
        url.append(getString(R.string.url)+"university"+"?token="+ SingIn.token);
        Log.i("tag","universityurl  "+url.toString());
        connect();
    }


    /**
     * Function to make the connection to get the desired unversities data and update the UI
     * @returns void
     * @author Ahmed Saleh
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
                                if(uni.getString("logo").contains("storage"))
                                {
                                    Log.i("tag","d5ll hnaaaaaaaaaaaaaaaaaaaaaa");
                                    String logo=getString(R.string.imageurl)+uni.getString("logo");
                                    headers.add(new ListItem(name,logo,id,"university"));}
                                else
                                {
                                    headers.add(new ListItem(name,id,"university"));
                                }
                                JSONArray faculties=uni.getJSONArray("faculties");
                                for(int j=0;j<faculties.length();++j)
                                {
                                    JSONObject faculty=faculties.getJSONObject(j);
                                    String facultyname=faculty.getString("name");

                                    int facultyid=faculty.getInt("id");
                                    if(faculty.getString("logo").contains("storage"))
                                    {

                                        String facultylogo=getString(R.string.imageurl)+faculty.getString("logo");
                                    childs.add(new ListItem(facultyname,facultylogo,facultyid,"faculty"));


                                    }
                                    else
                                    {
                                        childs.add(new ListItem(facultyname,facultyid,"faculty"));
                                    }
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



