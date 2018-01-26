package com.example.umaransari.myproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {

    DBHandler dbHandler;
    String Blood,Country;
    String blood,country,name,phone;
    DonorAdapter donorAdapter;
    Activity activity;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        dbHandler = new DBHandler(this);

        Intent intent = getIntent();
        Blood = intent.getStringExtra("Blood");
        Country = intent.getStringExtra("Country");

        Background bg = new Background(this);
        bg.execute("Search");

        ListView lv = (ListView)findViewById(R.id.ListView);

    }

    public class Background extends AsyncTask<String,Donor,String>{

        Context ctx;
        Background(Context ctx){
            this.ctx = ctx;
            activity = (Activity)ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            if(s.equals("Search")){
                listView.setAdapter(donorAdapter);
            }
            else
            {
                Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Donor... values) {

            donorAdapter.add(values[0]);
        }

        @Override
        protected String doInBackground(String... strings) {

            String method = strings[0];
            dbHandler = new DBHandler(ctx);

            if(method.equals("Search")){

                listView = (ListView) activity.findViewById(R.id.ListView);
                Cursor r = dbHandler.getData(Blood,Country);
                donorAdapter = new DonorAdapter(ctx,R.layout.searchlistlayout);

                while(r.moveToNext()){
                    name = r.getString(r.getColumnIndex("Name"));
                    country = r.getString(r.getColumnIndex("Country"));
                    blood = r.getString(r.getColumnIndex("BloodGroup"));
                    phone = r.getString(r.getColumnIndex("Phone"));
                    Donor donor = new Donor(name,blood,country,phone);
                    publishProgress(donor);
                }
                return "Search";
            }
            return null;
        }
    }
}
