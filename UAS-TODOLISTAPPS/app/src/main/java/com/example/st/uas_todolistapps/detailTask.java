package com.example.st.uas_todolistapps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class detailTask extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioButton work,school,home,shooping,other ;
    private EditText taskName,notes ;
    private int id ;
    private Button update, cancel,delete;
    private CheckBox done;
    dbHandlerData dbData ;
    Task dataTask ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);


        //Start
        //Start
        work = (RadioButton) findViewById(R.id.workRBdetail);
        school = (RadioButton) findViewById(R.id.schoolRBdetail);
        home = (RadioButton) findViewById(R.id.homeRbdetail);
        shooping = (RadioButton) findViewById(R.id.shoopingRBdetail);
        other = (RadioButton) findViewById(R.id.otherRBdetail);

        delete = (Button) findViewById(R.id.deleteDetail);
        cancel = (Button) findViewById(R.id.addCanceldetail);
        update = (Button) findViewById(R.id.updateDetail);

        taskName = (EditText) findViewById(R.id.addTaskNamedetail);
        notes = (EditText) findViewById(R.id.addNotesdetail) ;

        done = (CheckBox) findViewById(R.id.checkBox);

        dbData = new dbHandlerData(this);

        id = Integer.valueOf(getIntent().getStringExtra("id"));

        dataTask = dbData.getOneTask(id);

        if(dataTask.getCategory_id() == 1)
        {
            work.setChecked(true);
        }

        else if(dataTask.getCategory_id() == 2)
        {
            school.setChecked(true);
        }

        else if(dataTask.getCategory_id() == 3)
        {
            home.setChecked(true);
        }

        else if(dataTask.getCategory_id() == 4)
        {
            shooping.setChecked(true);
        }

        else if(dataTask.getCategory_id() == 5)
        {
            other.setChecked(true);
        }

        taskName.setText(dataTask.getTask_name());
        notes.setText(dataTask.getNotes());

        if(dataTask.getFlag_done() == 1)
        {
            done.setChecked(true);
        }

        else if(dataTask.getFlag_done() == 0)
        {
            done.setChecked(false);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskName.getText().toString().equals(""))
                {
                    Toast.makeText(detailTask.this, "Task Name cannot be empty", Toast.LENGTH_LONG).show();
                }

                else
                {
                    int categoryTemp = 0;

                    if (work.isChecked()) {
                        categoryTemp = 1;
                    } else if (school.isChecked()) {
                        categoryTemp = 2;
                    } else if (home.isChecked()) {
                        categoryTemp = 3;
                    } else if (shooping.isChecked()) {
                        categoryTemp = 4;
                    } else if (other.isChecked()) {
                        categoryTemp = 5;
                    }

                    int isDone = 0;

                    if (done.isChecked()) {
                        isDone = 1;
                    }

                    Task kirimTask = new Task(dataTask.getId(), taskName.getText().toString(),
                            notes.getText().toString(), categoryTemp, isDone);

                    updateAlert(kirimTask);


                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteAlert(dataTask.getId());

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

    private void deleteAlert(final int id) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEUTRAL:
                        //tombol dismiss
                        break;

                    case DialogInterface.BUTTON_POSITIVE:
                        dbData.deleteDataTask(dataTask.getId());
                        Toast.makeText(detailTask.this, "Success", Toast.LENGTH_LONG).show();
                        finish();
                        break;

                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to Delete ?").setTitle("Confirm Delete").setNeutralButton("CANCEL", dialogClickListener).setPositiveButton("DELETE",dialogClickListener).show();
    }

    private void updateAlert(final Task task2) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEUTRAL:
                        //tombol dismiss
                        break;

                    case DialogInterface.BUTTON_POSITIVE:
                        dbData.UpdateDataTask(task2);

                        Toast.makeText(detailTask.this, "Success", Toast.LENGTH_LONG).show();
                        finish();
                        break;

                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to Update ?").setTitle("Confirm Update").setNeutralButton("CANCEL", dialogClickListener).setPositiveButton("UPDATE",dialogClickListener).show();
    }
}
