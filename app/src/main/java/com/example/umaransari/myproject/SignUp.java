package com.example.umaransari.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class SignUp extends AppCompatActivity implements  Spinner.OnItemSelectedListener{

    RadioGroup rg;
    RadioButton rb;
    Button b;
    EditText name,email,password,day,year,address,phone,SecAnswer;
    DBHandler dbHandler;
    Spinner country,blood,month,SecQuestion;
    String Gender,Country,Name,Email,Password,Day,Year,Address,Phone,Answer,Question,Blood,Month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.Name);
        email = (EditText)findViewById(R.id.Email);
        password = (EditText)findViewById(R.id.Password);
        day = (EditText)findViewById(R.id.Day);
        year = (EditText)findViewById(R.id.Year);
        address = (EditText)findViewById(R.id.Address);
        phone = (EditText)findViewById(R.id.Phone);
        SecAnswer = (EditText)findViewById(R.id.Answer);

        rg = (RadioGroup) findViewById(R.id.Radio);

        b = (Button) findViewById(R.id.btnSignup);

        dbHandler = new DBHandler(this);

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        SpinnerSet(countries);

    }

    public void SpinnerSet(ArrayList countries){
        //Country Spinner
        country = (Spinner) findViewById(R.id.Country);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        country.setAdapter(adapter1);
        country.setOnItemSelectedListener(this);

        //Blood Group Spinner
        blood =  (Spinner) findViewById(R.id.BloodGroup);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.BloodGroup, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(adapter3);
        blood.setOnItemSelectedListener(this);

        //Security Question Spinner
        SecQuestion = (Spinner) findViewById(R.id.securityQuestion);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.Security, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SecQuestion.setAdapter(adapter4);
        SecQuestion.setOnItemSelectedListener(this);

        //Month Spinner
        month = (Spinner) findViewById(R.id.Month);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.Months, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapter5);
        month.setOnItemSelectedListener(this);
    }

    public void rbListener (View v){

        int rbid = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(rbid);
        Gender = rb.getText().toString();
        System.out.println(Gender);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {

        switch(parent.getId()){
            case R.id.BloodGroup:
                Blood = blood.getItemAtPosition(pos).toString();
                break;
            case R.id.Month:
                Month = month.getItemAtPosition(pos).toString();
                break;
            case R.id.Country:
                Country = country.getItemAtPosition(pos).toString();
                break;
            case R.id.securityQuestion:
                Question = SecQuestion.getItemAtPosition(pos).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void RedtoLogin(View v){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void SignUp(View v){

        Name = name.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        Day = day.getText().toString();
        Year = year.getText().toString();
        Address = address.getText().toString();
        Phone = phone.getText().toString();
        Answer = SecAnswer.getText().toString();

        String DOB = Day +"/" +Month +"/" +Year;

        long rid = dbHandler.insert(Name,Email,Password,Blood,Gender,DOB,Address,Country,Phone,Question,Answer);

        Toast.makeText(this,"rid is " +rid, Toast.LENGTH_LONG).show();

        if(rid != -1){
            Intent i = new Intent(SignUp.this, Login.class);
            startActivity(i);
        }

    }

}
