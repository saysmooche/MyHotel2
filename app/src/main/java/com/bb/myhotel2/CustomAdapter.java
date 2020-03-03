package com.bb.myhotel2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    static LayoutInflater inflater = null;
    int icons[];
    String guestFName[];
    String guestLName[];


    public CustomAdapter(Context context, int icons[], String guestFName[], String guestLName[]) {
        this.context = context;
        this.icons = icons;
        this.guestFName = guestFName;
        this.guestLName = guestLName;

    }

    @Override
    public int getCount() {
        return guestFName.length;
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            row = inflater.inflate(R.layout.list_of_rows, null);

        }
        ImageView img = (ImageView) row.findViewById(R.id.personimageView);
        EditText tv = (EditText) row.findViewById(R.id.fname2);
        EditText tvv = (EditText) row.findViewById(R.id.lname2);

        img.setImageResource(icons[position]);
        tv.setText(guestFName[position]);
        tvv.setText(guestLName[position]);


        return row;
    }
}
