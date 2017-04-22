package com.example.ahmedsaleh.dbse;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AboutDbSE extends AppCompatActivity {

    TextView description;
    TextView website;
    String result=null;
    StringBuilder URL;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_db_se);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        URL = new StringBuilder("http://a1a2b2dd.ngrok.io/dbse/public/api/v1/about");
        description = (TextView) findViewById(R.id.dbse_description);
        website = (TextView) findViewById(R.id.dbse_website);
        connect();
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
                            description.setText(json.getString("description"));
                            website.setText(json.getString("website"));
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
}
