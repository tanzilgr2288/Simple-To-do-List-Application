package com.example.st.uas_todolistapps;

import android.content.Intent;
import android.os.Handler;
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
import android.widget.Toast;

import java.util.ArrayList;

public class ListCategory extends AppCompatActivity {

    private String emailUser;
    private Toolbar toolbar;
    private ListView lv;
    private listViewAdapter adapter;
    private ArrayList<String> data;
    private TextView nameCategory ;
    private dbHandlerUser dbUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);


        //Start

        lv = (ListView) findViewById(R.id.listViewCategory);
        data = new ArrayList<String>() ;

        adapter = new listViewAdapter(ListCategory.this,data);
        lv.setAdapter(adapter);

        data.add("All");
        data.add("Work");
        data.add("School");
        data.add("Home");
        data.add("Shopping");
        data.add("Other");

        adapter.notifyDataSetChanged();

        dbUser = new dbHandlerUser(this);

        emailUser = getIntent().getStringExtra("emailUser");

        nameCategory = (TextView) findViewById(R.id.nameCategory);

        nameCategory.setText("Welcome, "+dbUser.getUserName(emailUser));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    //All Category
                    Intent intent = new Intent(ListCategory.this, ListTask.class);
                    intent.putExtra("category", "0");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                else if(position == 1)
                {
                    //Work Category
                    Intent intent = new Intent(ListCategory.this, ListTask.class);
                    intent.putExtra("category", "1");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                else if(position == 2)
                {
                    //School Category
                    Intent intent = new Intent(ListCategory.this, ListTask.class);
                    intent.putExtra("category", "2");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                else if(position == 3)
                {
                    //Home Category
                    Intent intent = new Intent(ListCategory.this, ListTask.class);
                    intent.putExtra("category", "3");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                else if(position == 4)
                {
                    //Shopping Category
                    Intent intent = new Intent(ListCategory.this, ListTask.class);
                    intent.putExtra("category", "4");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

                else if(position == 5)
                {
                    //Other Category
                    Intent intent = new Intent(ListCategory.this, ListTask.class);
                    intent.putExtra("category", "5");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addTask)
        {
            //Add Task Start
            Intent intent = new Intent(ListCategory.this, addNewTask.class);
            intent.putExtra("category", "1");
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        else if (id == R.id.action_updateProfile)
        {
            //Update Profile Start
            Intent intent = new Intent(ListCategory.this, updateProfile.class);
            intent.putExtra("emailUser", emailUser);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }

        else if (id == R.id.action_about)
        {
            //About Start
            Intent intent = new Intent(ListCategory.this, about.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        else if (id == R.id.action_logout)
        {
            //Logout Start
            Intent intent = new Intent(ListCategory.this, login.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
