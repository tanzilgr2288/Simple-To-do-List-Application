package com.example.st.uas_todolistapps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ST on 6/12/2016.
 */
public class listViewAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> data;
    private LayoutInflater inflater;

    public listViewAdapter (Activity activity, ArrayList<String> data)
    {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String temp = data.get(position);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_view_category,null);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.nameListViewCategory);

        textName.setText(temp);

        return convertView ;
    }
}
