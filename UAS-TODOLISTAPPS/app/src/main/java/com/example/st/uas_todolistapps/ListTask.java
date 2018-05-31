package com.example.st.uas_todolistapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListTask extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lv;
    private listViewAdapterForListTask adapter;
    private ArrayList<Task> data;
    private TextView categoryName ;
    private int categoryCode;
    private dbHandlerData dbdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);




        //Start

        categoryName = (TextView) findViewById(R.id.categoryListTask);

        categoryCode = Integer.valueOf(getIntent().getStringExtra("category"));

        lv = (ListView) findViewById(R.id.listViewTask);
        data = new ArrayList<Task>() ;

        adapter = new listViewAdapterForListTask(ListTask.this,data);
        lv.setAdapter(adapter);

        dbdata = new dbHandlerData(this);

        ArrayList<Task> tumbal = new ArrayList<Task>();

        tumbal = dbdata.getDataCategory(categoryCode);


        //for urut, biar rapi saja
        for(int i=0 ; i<tumbal.size() ;i++)
        {
            if(tumbal.get(i).getFlag_done()== 0)
            {
                data.add(tumbal.get(i));
            }
        }

        for(int i=0 ; i<tumbal.size() ;i++)
        {
            if(tumbal.get(i).getFlag_done()== 1)
            {
                data.add(tumbal.get(i));
            }
        }

        adapter.notifyDataSetChanged();



        if(categoryCode == 1)
        {
            categoryName.setText("Work");
        }

        else if(categoryCode == 2)
        {
            categoryName.setText("School");
        }

        else if(categoryCode == 3)
        {
            categoryName.setText("Home");
        }

        else if(categoryCode == 4)
        {
            categoryName.setText("Shopping");
        }

        else if(categoryCode == 5)
        {
            categoryName.setText("Other");
        }

        else if(categoryCode == 0)
        {
            categoryName.setText("All");
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task tempTask = data.get(position);

                Intent intent = new Intent(ListTask.this, detailTask.class);
                intent.putExtra("id", ""+tempTask.getId());
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // do something

            finish();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addTaskTaskArea)
        {
            //Add task from area
            Intent intent = new Intent(ListTask.this, addNewTask.class);
            intent.putExtra("category", ""+categoryCode);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        }

        else if (id == R.id.action_Refresh)
        {
            //Refresh Task
            ArrayList<Task> tumbal = new ArrayList<Task>();

            tumbal = dbdata.getDataCategory(categoryCode);

            data.clear();


            //for urut, biar rapi saja
            for(int i=0 ; i<tumbal.size() ;i++)
            {
                if(tumbal.get(i).getFlag_done()== 0)
                {
                    data.add(tumbal.get(i));
                }
            }

            for(int i=0 ; i<tumbal.size() ;i++)
            {
                if(tumbal.get(i).getFlag_done()== 1)
                {
                    data.add(tumbal.get(i));
                }
            }

            adapter.notifyDataSetChanged();

        }

        return true;

        //return super.onOptionsItemSelected(item);
    }
}
