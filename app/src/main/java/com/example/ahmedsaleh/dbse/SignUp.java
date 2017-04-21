package com.example.ahmedsaleh.dbse;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class SignUp extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    EditText realname;
    Button createaccount;
    Button nextbutton;
    CheckBox male;
    CheckBox female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username =(EditText)findViewById(R.id.usernameedittext);
        email =(EditText)findViewById(R.id.emailedittext);
        password =(EditText)findViewById(R.id.passwordedittext);
        realname =(EditText)findViewById(R.id.real_user_name_editText);
        createaccount =(Button) findViewById(R.id.createaccount_button);
        nextbutton =(Button) findViewById(R.id.next_step);
        male = (CheckBox) findViewById(R.id.male_checkbox) ;
        female = (CheckBox) findViewById(R.id.female_checkbox) ;
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate());
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    nextbutton.setVisibility(View.INVISIBLE);
                    createaccount.setVisibility(View.VISIBLE);
                    realname.setVisibility(View.VISIBLE);
                    male.setVisibility(View.VISIBLE);
                    female.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    //  this function for validating the user input for regiseration
    boolean validate()
    {
        if(username.getText().toString().isEmpty())
        {
            username.setError("User Name "+(getString(R.string.emptyerror)));
            return false;
        }
        if(email.getText().toString().isEmpty())
        {
            email.setError("E-mail "+getString(R.string.emptyerror));
            return false;
        }
        if(password.getText().toString().isEmpty())
        {
            password.setError("password "+getString(R.string.emptyerror));
            return false;
        }
        if(!verifyemail())return false;
        return true;
    }
    boolean verifyemail()
    {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(SignUp.this);
        View mview=getLayoutInflater().inflate(R.layout.codeinputdialog,null);
        mBuilder.setView(mview);
        AlertDialog dialog=mBuilder.create();
        dialog.show();
        Button submitConfirmationCode = (Button)dialog.findViewById(R.id.confirmation_code_Button);
        submitConfirmationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return true;
    }
//    void connectToGet()
//    {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(URL.toString())
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                Log.v("responsehhhhhhhhh", call.request().body().toString());
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, Response response) throws IOException
//            {
//                result=response.body().string().toString();
//                Log.v("Response code", String.valueOf(response.code()));
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run()
//                    {
//
//                        try {
//                            JSONObject json = new JSONObject(result);
//                            realName.setText(json.get("name").toString(), TextView.BufferType.EDITABLE);
//                            userName.setText(json.get("username").toString(), TextView.BufferType.EDITABLE);
//                            email.setText(json.get("email").toString(), TextView.BufferType.EDITABLE);
//                            password.setText(json.get("password").toString(), TextView.BufferType.EDITABLE);
//                            userGender.setText(json.get("gender").toString(), TextView.BufferType.EDITABLE);
//                        } catch (JSONException e) {
//
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//            }
//        });
//
//    }
//    void connectToPost() {
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("username", String.valueOf(userName.getText()));
//        params.put("email", String.valueOf(email.getText()));
//        params.put("password", String.valueOf(password.getText()));
//        params.put("name", String.valueOf(realName.getText()));
//        params.put("gender", String.valueOf(userGender.getText()));
//        params.put("id", String.valueOf(SingIn.id));
//        JSONObject parameter = new JSONObject(params);
//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(JSON, parameter.toString());
//        Request request = new Request.Builder()
//                .url(URL.toString())
//                .put(body)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                Toast.makeText(EditProfile.this, "Connection Failed", Toast.LENGTH_LONG).show();
//                Log.v("responsehhhhhhhhh", call.request().body().toString());
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
//                result = response.body().string().toString();
//                Log.v("Response code", String.valueOf(response.code()));
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            JSONObject json = new JSONObject(result);
//                            String error = json.get("error").toString();
//                            Toast.makeText(EditProfile.this, error, Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_LONG).show();
//                            moveToViewProfileActivity();
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//            }
//        });
//    }
}
