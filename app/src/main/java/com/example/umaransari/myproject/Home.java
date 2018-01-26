package com.example.umaransari.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Home extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    Spinner blood, country;
    String Blood, Country;
    DBHandler dbHandler;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

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

    public void SpinnerSet(ArrayList countries) {
        //Country Spinner
        country = (Spinner) findViewById(R.id.Country);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        country.setAdapter(adapter1);
        country.setOnItemSelectedListener(this);

        //Blood Group Spinner
        blood = (Spinner) findViewById(R.id.BloodGroup);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.BloodGroup, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(adapter3);
        blood.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {

        switch(parent.getId()){
            case R.id.BloodGroup:
                Blood = blood.getItemAtPosition(pos).toString();
                break;

            case R.id.Country:
                Country = country.getItemAtPosition(pos).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClick(View v){
        if(v.getId() == R.id.search){

            Intent i = new Intent(this, SearchResult.class);
            i.putExtra("Blood",Blood);
            i.putExtra("Country",Country);
            startActivity(i);
        }
    }
}
