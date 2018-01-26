package com.example.umaransari.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button btn;
    TextView t1;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.LEmail);
        password = (EditText)findViewById(R.id.LPassword);
        t1 = (TextView) findViewById(R.id.textSignup);
        btn = (Button)findViewById(R.id.btnLogin);

        dbHandler = new DBHandler(this);
    }

    public void onClick(View v){
        switch(v.getId()){

            case R.id.btnLogin:
                String em = email.getText().toString();
                String pas = password.getText().toString();
                boolean i = dbHandler.checkUser(em,pas);

                if(i){
                    Intent intent = new Intent(this,Home.class);
                    startActivity(intent);
                    finish();
                }

                else
                {
                    Toast.makeText(this, "User Doesn't exist", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.textSignup:
                Intent rd = new Intent(Login.this, SignUp.class);
                startActivity(rd);
                break;
            case R.id.forgotpassword:
                Intent in = new Intent (Login.this, ForgotPassword.class);
                startActivity(in);
                break;

        }
    }
}