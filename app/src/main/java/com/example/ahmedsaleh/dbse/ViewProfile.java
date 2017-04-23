package com.example.ahmedsaleh.dbse;

import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

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

public class ViewProfile extends AppCompatActivity {

    TextView firstLetter;
    String result=null;
    StringBuilder URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);



        firstLetter = (TextView) findViewById(R.id.View_Profile_textincircle);
        URL = new StringBuilder("http://a1a2b2dd.ngrok.io/dbse/public/api/v1/visitor/"+String.valueOf(SingIn.id)+"?token=");
        URL.append(SingIn.token);
//        connect();
    }

    void connect()
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL.toString())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.v("responsehhhhhhhhh", call.request().body().toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException
            {
                result=response.body().string().toString();
                Log.v("Response code", String.valueOf(response.code()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try {
                            JSONObject json =new JSONObject(result);
                            TextView name = (TextView) findViewById(R.id.View_Profile_username_header);
                            name.setText(json.getString("name"));
                            TextView mail = (TextView) findViewById(R.id.View_Profile_email_header);
                            mail.setText(json.getString("email"));
                            firstLetter.setText(String.valueOf(Character.toUpperCase(json.getString("name").charAt(0))));
                            TextView username = (TextView) findViewById(R.id.username_viewprofile2);
                            username.setText(json.get("username").toString());
                            TextView gender = (TextView) findViewById(R.id.Gender_viewprofile2);
                            gender.setText(json.get("gender").toString());
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
}
