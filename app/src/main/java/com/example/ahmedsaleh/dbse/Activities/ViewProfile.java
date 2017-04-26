package com.example.ahmedsaleh.dbse.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedsaleh.dbse.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
        URL = new StringBuilder(getString(R.string.url)+"visitor/"+String.valueOf(SingIn.id)+"?token=");
        URL.append(SingIn.token);
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ViewProfile.this,"Connection Failed!", Toast.LENGTH_LONG).show();
                    }
                });
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
