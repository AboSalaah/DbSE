package com.example.ahmedsaleh.dbse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedsaleh.dbse.R;

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
    CheckBox male;
    CheckBox female;
    Button changeGenderButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        realName = (EditText) findViewById(R.id.real_user_name);
        userName = (EditText) findViewById(R.id.new_username);
        email = (EditText) findViewById(R.id.new_email);
        password = (EditText) findViewById(R.id.new_password);
        male = (CheckBox) findViewById(R.id.male_checkbox_editProfile) ;
        female = (CheckBox) findViewById(R.id.female_checkbox_editProfile) ;
        changeGenderButton = (Button) findViewById(R.id.chooseGender_editProfile) ;
        changeGenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked()|| female.isChecked()){
                    male.toggle();
                    female.toggle();
                } else {
                    male.toggle();
                }
            }
        });
        URL = new StringBuilder(getString(R.string.url)+"visitor/"+String.valueOf(SingIn.id)+"?token=");
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(EditProfile.this,"Connection Failed!", Toast.LENGTH_LONG).show();
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
                            JSONObject json = new JSONObject(result);
                            realName.setText(json.get("name").toString(), TextView.BufferType.EDITABLE);
                            userName.setText(json.get("username").toString(), TextView.BufferType.EDITABLE);
                            email.setText(json.get("email").toString(), TextView.BufferType.EDITABLE);
                            password.setText(json.get("password").toString(), TextView.BufferType.EDITABLE);
                            String gender = json.get("gender").toString();
                            if(gender.equals("MALE")){
                                male.toggle();
                            } else {
                                female.toggle();
                            }
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
        if(male.isChecked()){
            params.put("gender","MALE");
        } else {
            params.put("gender","FEMALE");
        }
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
                Log.v("responsehhhhhhhhh", call.request().body().toString());
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(EditProfile.this,"Connection Failed!", Toast.LENGTH_LONG).show();
                    }
                });
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
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }
}
