package com.example.ahmedsaleh.dbse;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       Button createaccount=(Button) findViewById(R.id.createaccount_button);
       createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(validate());
            }
        });
    }
//  this function for validating the user input for regiseration
    boolean validate()
    {
        EditText username=(EditText)findViewById(R.id.usernameedittext);
        EditText email=(EditText)findViewById(R.id.emailedittext);
        EditText password=(EditText)findViewById(R.id.passwordedittext);
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
        return true;
    }
}
