package com.example.umaransari.myproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText Email, Answer;
    TextView Question,tvPass,tv;
    Button Proceed,pass,redlogin;
    DBHandler dbHandler;
    String ques, em, ans, pas,answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        dbHandler  = new DBHandler(this);
        Email = (EditText)findViewById(R.id.fpEmail);
        Answer = (EditText)findViewById(R.id.fpanswer);
        Question = (TextView)findViewById(R.id.Question);
        tvPass = (TextView)findViewById(R.id.fppass);
        tv = (TextView)findViewById(R.id.text);
        Proceed = (Button)findViewById(R.id.proceed);
        pass = (Button)findViewById(R.id.GetPass);
        redlogin = (Button)findViewById(R.id.redLogin);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.proceed:
                em = Email.getText().toString();
                Cursor r = dbHandler.getQA(em);
                if(r != null){
                    if(r.getCount() > 0){

                        //String ques = cursor.getString(10);
                        //return cursor;
                        ques = r.getString(r.getColumnIndex("Question"));
                        answer = r.getString(r.getColumnIndex("Answer"));
                        pas = r.getString(r.getColumnIndex("Password"));

                        Email.setVisibility(View.INVISIBLE);
                        Proceed.setVisibility(View.INVISIBLE);
                        Question.setVisibility(View.VISIBLE);
                        Answer.setVisibility(View.VISIBLE);
                        pass.setVisibility(View.VISIBLE);
                        Question.setText(ques);
                    }
                }

               // if(ques != "No user found"){

               // }
                else
                {
                    Toast.makeText(this, ques, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.GetPass:
                ans = Answer.getText().toString();

                if(ans == answer){
                    Question.setVisibility(View.INVISIBLE);
                    Answer.setVisibility(View.INVISIBLE);
                    pass.setVisibility(View.INVISIBLE);
                    redlogin.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    tvPass.setVisibility(View.VISIBLE);
                    tvPass.setText(pas);
                }
                break;
            case R.id.redLogin:
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
