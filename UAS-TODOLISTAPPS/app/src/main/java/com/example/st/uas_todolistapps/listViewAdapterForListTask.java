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
 * Created by ST on 6/13/2016.
 */
public class listViewAdapterForListTask extends BaseAdapter {

    private Activity activity;
    private ArrayList<Task> data;
    private LayoutInflater inflater;

    public listViewAdapterForListTask (Activity activity, ArrayList<Task> data)
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
        Task temp = data.get(position);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_view_list_task,null);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.nameListTask);
        TextView category = (TextView) convertView.findViewById(R.id.categoryListTask);

        if(temp.getFlag_done() == 0)
        {
            textName.setTextColor(activity.getResources().getColor(R.color.red));
        }

        else
        {
            textName.setTextColor(activity.getResources().getColor(R.color.green));
        }

        textName.setText(temp.getTask_name());

        String categoryText="" ;

        if(temp.category_id == 1)
        {
            categoryText = "Work";
        }

        if(temp.category_id == 2)
        {
            categoryText = "School";
        }

        if(temp.category_id == 3)
        {
            categoryText = "Home";
        }

        if(temp.category_id == 4)
        {
            categoryText = "Shooping";
        }

        if(temp.category_id == 5)
        {
            categoryText = "Other";
        }


        category.setText(categoryText);

        return convertView ;
    }
}
