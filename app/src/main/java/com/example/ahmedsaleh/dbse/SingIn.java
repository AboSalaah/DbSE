package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.data;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class SingIn extends AppCompatActivity {

    EditText userOrEmail;
    EditText password;
    Button signInButton;
    TextView forgotPassword;
    TextView signUpTextView;
    ImageView facebookPage;
    String result=null;
    StringBuilder URL;
    public static String token;
    public static int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL = new StringBuilder("http://2fe2762f.ngrok.io/dbse/public/api/v1/signin");
        userOrEmail = (EditText) findViewById(R.id.signIn_user_email);
        password = (EditText) findViewById(R.id.signIn_password);
        signInButton=(Button) findViewById(R.id.sign_in_button);
        facebookPage =(ImageView) findViewById(R.id.Facebook_Button);
        signUpTextView = (TextView) findViewById(R.id.SignIn_SignUp_text);
        forgotPassword = (TextView) findViewById(R.id.forget_password) ;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                    connect();
                }
            }
        });
        facebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent facebookIntent = openBbSE_FacebookPage(SingIn.this);
                Intent chooser = Intent.createChooser(facebookIntent,"Open By");
                startActivity(chooser);
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToSignUpActivity();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(SingIn.this);
                View mview=getLayoutInflater().inflate(R.layout.forgot_password_dialog,null);
                mBuilder.setView(mview);
                AlertDialog dialog=mBuilder.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_signUp) {
            moveToSignUpActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //  this function for validating the user input for signin
    private boolean validate()
    {
        EditText email=(EditText)findViewById(R.id.signIn_user_email);
        EditText password=(EditText)findViewById(R.id.signIn_password);
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
        return true;
    }
    private void moveToSignUpActivity(){
        Intent i = new Intent(SingIn.this,SignUp.class);
        startActivity(i);
    }
    private void moveToUniversitiesActivity(){
        Intent i = new Intent(SingIn.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    public static Intent openBbSE_FacebookPage(Context context) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/1220130878052358"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/pg/DatabaseSE.CO"));
        }
    }

    void connect()
    {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();
        params.put("login", String.valueOf(userOrEmail.getText()));
        params.put("password", String.valueOf(password.getText()));
        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url(URL.toString())
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
//                        Toast.makeText(SingIn.this,"Connection Failed", Toast.LENGTH_LONG).show();
                Log.v("responsehhhhhhhhh", call.request().body().toString());
                Log.v("error",e.toString());
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
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                                    .putString("token", json.getString("token")).commit();
                            token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                                    .getString("token", "defaultStringIfNothingFound");
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                                    .putString("id", json.getString("id")).commit();
                            id = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                                    .getString("id", "defaultStringIfNothingFound"));
                            String error = json.get("error").toString();
//                                    Toast.makeText(SingIn.this,error, Toast.LENGTH_LONG).show();
//                                    moveToSignUpActivity();
                        } catch (JSONException e) {
                            moveToUniversitiesActivity();
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
//        Toast.makeText(SingIn.this,"Connection Failed", Toast.LENGTH_LONG).show();
    }


}
