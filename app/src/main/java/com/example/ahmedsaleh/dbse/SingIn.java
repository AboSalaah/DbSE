package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SingIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createaccount=(Button) findViewById(R.id.sign_in_button);
        Button facebookPage =(Button) findViewById(R.id.Facebook_Button);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
        Intent i = new Intent(getApplicationContext(),SignUp.class);
        startActivity(i);
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

}
