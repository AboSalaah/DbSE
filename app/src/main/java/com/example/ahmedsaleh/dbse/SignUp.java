package com.example.ahmedsaleh.dbse;

import android.content.DialogInterface;
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
import java.net.URL;
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
    Button change;
    CheckBox male;
    CheckBox female;
    Map<String, String> params;

    String result=null;
    StringBuilder URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        URL = new StringBuilder("http://a3534e47.ngrok.io/dbse/public/api/v1/signup");
        username =(EditText)findViewById(R.id.usernameedittext);
        email =(EditText)findViewById(R.id.emailedittext);
        password =(EditText)findViewById(R.id.passwordedittext);
        realname =(EditText)findViewById(R.id.real_user_name_editText);
        createaccount =(Button) findViewById(R.id.createaccount_button);
        nextbutton =(Button) findViewById(R.id.next_step);
        change = (Button) findViewById(R.id.chooseSex);
        male = (CheckBox) findViewById(R.id.male_checkbox);
        female = (CheckBox) findViewById(R.id.female_checkbox);
        male.toggle();
        params = new HashMap<String, String>();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.toggle();
                female.toggle();
            }
        });

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateOtherData()){
//                    Toast.makeText(SignUp.this, "Done", Toast.LENGTH_LONG).show();
                        if(male.isChecked()){
                            params.put("gender","MALE");
                        }else{
                            params.put("gender","FEMALE");
                        }
                        params.put("name",realname.getText().toString());
                        connectToPost();
                }
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    params = new HashMap<String, String>();
                    params.put("username",username.getText().toString());
                    params.put("email",email.getText().toString());
                    params.put("password",password.getText().toString());
                    connectToPost();
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
        mBuilder.setTitle(R.string.confirmationcode);
        mBuilder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                nextbutton.setVisibility(View.INVISIBLE);
                createaccount.setVisibility(View.VISIBLE);
                realname.setVisibility(View.VISIBLE);
                male.setVisibility(View.VISIBLE);
                female.setVisibility(View.VISIBLE);
                change.setVisibility(View.VISIBLE);
            }
        });
        mBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        mBuilder.setView(mview);
        AlertDialog dialog=mBuilder.create();
        dialog.show();
        return true;
    }

    boolean validateOtherData(){
        if(realname.getText().toString().isEmpty()){
            realname.setError("User Name "+(getString(R.string.emptyerror)));
            return false;
        }
        return true;
    }

    void connectToPost() {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
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
                Toast.makeText(SignUp.this, "Connection Failed", Toast.LENGTH_LONG).show();
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
                            Toast.makeText(SignUp.this, error, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            try {
                                JSONObject json = new JSONObject(result);
                                Toast.makeText(SignUp.this,json.get("msg").toString() , Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            } catch (JSONException ex){
                                ex.printStackTrace();
                            }
                        }

                    }
                });

            }
        });
    }
}
