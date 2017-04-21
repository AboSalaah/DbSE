package com.example.ahmedsaleh.dbse;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Hamada on 4/18/2017.
 */

public class EditProfile extends AppCompatActivity {

    String result=null;
    StringBuilder URL;
    EditText realName;
    EditText userName;
    EditText email;
    EditText password;
    EditText userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        realName = (EditText) findViewById(R.id.real_user_name);
        userName = (EditText) findViewById(R.id.new_username);
        email = (EditText) findViewById(R.id.new_email);
        password = (EditText) findViewById(R.id.new_password);
        userGender = (EditText) findViewById(R.id.user_gender) ;
        URL = new StringBuilder("http://a3534e47.ngrok.io/dbse/public/api/v1/visitor/"+String.valueOf(SingIn.id)+"?token=");
        Log.v("myyyyyyyyyyyyyyyy",String.valueOf(SingIn.id));
        URL.append(SingIn.token);
        connectToGet();
        Button saveButton = (Button) findViewById(R.id.Save_changes_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectToPost();
            }
        });
    }

    void connectToGet()
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL.toString())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.v("responsehhhhhhhhh", call.request().body().toString());
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
                            JSONObject json = new JSONObject(result);
                            realName.setText(json.get("name").toString(), TextView.BufferType.EDITABLE);
                            userName.setText(json.get("username").toString(), TextView.BufferType.EDITABLE);
                            email.setText(json.get("email").toString(), TextView.BufferType.EDITABLE);
                            password.setText(json.get("password").toString(), TextView.BufferType.EDITABLE);
                            userGender.setText(json.get("gender").toString(), TextView.BufferType.EDITABLE);
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
    void connectToPost() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", String.valueOf(userName.getText()));
        params.put("email", String.valueOf(email.getText()));
        params.put("password", String.valueOf(password.getText()));
        params.put("name", String.valueOf(realName.getText()));
        params.put("gender", String.valueOf(userGender.getText()));
        params.put("id", String.valueOf(SingIn.id));
        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url(URL.toString())
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Toast.makeText(EditProfile.this, "Connection Failed", Toast.LENGTH_LONG).show();
                Log.v("responsehhhhhhhhh", call.request().body().toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
                result = response.body().string().toString();
                Log.v("Response code", String.valueOf(response.code()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(result);
                            String error = json.get("error").toString();
                            Toast.makeText(EditProfile.this, error, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_LONG).show();
                            moveToViewProfileActivity();
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }
    void moveToViewProfileActivity(){
        Intent i = new Intent(EditProfile.this,ViewProfile.class);
        startActivity(i);
    }
}
