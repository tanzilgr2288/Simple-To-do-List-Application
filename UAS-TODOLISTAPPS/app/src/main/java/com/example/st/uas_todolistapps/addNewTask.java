package com.example.st.uas_todolistapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class addNewTask extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioButton work,school,home,shooping,other ;
    private EditText taskName,notes ;
    private int codeCategoryFrom ;
    private Button save, cancel;
    dbHandlerData dbData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);


        //Start
        work = (RadioButton) findViewById(R.id.workRB);
        school = (RadioButton) findViewById(R.id.schoolRB);
        home = (RadioButton) findViewById(R.id.homeRb);
        shooping = (RadioButton) findViewById(R.id.shoopingRB);
        other = (RadioButton) findViewById(R.id.otherRB);

        save = (Button) findViewById(R.id.addSave);
        cancel = (Button) findViewById(R.id.addCancel);

        taskName = (EditText) findViewById(R.id.addTaskName);
        notes = (EditText) findViewById(R.id.addNotes) ;

        dbData = new dbHandlerData(this);

        codeCategoryFrom = Integer.valueOf(getIntent().getStringExtra("category"));


        if (codeCategoryFrom == 1)
        {
            work.setChecked(true);
        }

        else if (codeCategoryFrom == 2)
        {
            school.setChecked(true);
        }
        else if (codeCategoryFrom == 3)
        {
            home.setChecked(true);
        }
        else if (codeCategoryFrom == 4)
        {
            shooping.setChecked(true);
        }
        else if (codeCategoryFrom == 5)
        {
            other.setChecked(true);
        }

        else if (codeCategoryFrom == 0)
        {
            work.setChecked(true);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskName.getText().toString().equals(""))
                {
                    Toast.makeText(addNewTask.this, "Task Name cannot be empty", Toast.LENGTH_LONG).show();
                }

                else
                {
                    if(work.isChecked())
                    {
                        codeCategoryFrom = 1;
                    }

                    else if(school.isChecked())
                    {
                        codeCategoryFrom = 2;
                    }

                    else if(home.isChecked())
                    {
                        codeCategoryFrom = 3;
                    }

                    else if(shooping.isChecked())
                    {
                        codeCategoryFrom = 4;
                    }
                    else if(other.isChecked())
                    {
                        codeCategoryFrom = 5;
                    }

                    Task taskTemp = new Task(0,taskName.getText().toString(),
                            notes.getText().toString(),codeCategoryFrom,0);

                    dbData.addTask(taskTemp);

                    Toast.makeText(addNewTask.this, "Success", Toast.LENGTH_LONG).show();
                    finish();

                }
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
}
