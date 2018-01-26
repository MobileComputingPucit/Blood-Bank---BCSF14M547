package com.example.umaransari.myproject;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umar Ansari on 26/01/2018.
 */

public class DonorAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public DonorAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Donor object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public DonorAdapter( Context context,  int resource,  int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View row = convertView;
        donorHolder donorholder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.searchlistlayout,parent,false);

            donorholder = new donorHolder();
            donorholder.tv_Name = (TextView)row.findViewById(R.id.LayName);
            donorholder.tv_Blood = (TextView)row.findViewById(R.id.LayBlood);
            donorholder.tv_Country = (TextView)row.findViewById(R.id.LayCountry);
            row.setTag(donorholder);
        }
        else
        {
            donorholder = (donorHolder)row.getTag();
        }

        Donor donor = (Donor)getItem(position);
        donorholder.tv_Name.setText(donor.getName().toString());
        donorholder.tv_Blood.setText(donor.getBlood().toString());
        donorholder.tv_Country.setText(donor.getCountry().toString());

        return row;
    }

    static class donorHolder{
        TextView tv_Name, tv_Blood, tv_Country;

    }
}
