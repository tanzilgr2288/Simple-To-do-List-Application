package com.example.st.uas_todolistapps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class updateProfile extends AppCompatActivity {

    private Toolbar toolbar;
    private Button submit,cancel;
    private EditText email,pass,repass,name;
    private RadioButton male,female;
    final private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private dbHandlerUser dbUser ;
    private String emailUser;
    private User userHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);


        //Start

        emailUser = getIntent().getStringExtra("emailUser");

        submit = (Button) findViewById(R.id.submitRegister2);
        cancel = (Button) findViewById(R.id.cancelRegister2);

        email = (EditText) findViewById(R.id.emailRegister2);
        pass = (EditText) findViewById(R.id.passwordRegister2);
        repass = (EditText) findViewById(R.id.repasswordRegister2);
        name = (EditText) findViewById(R.id.fullNameRegister2);

        male = (RadioButton) findViewById(R.id.maleRB2);
        female = (RadioButton) findViewById(R.id.femaleRB2);

        dbUser = new dbHandlerUser(this);

        userHelp = dbUser.getUserDetail(emailUser);

        email.setText(userHelp.getEmail());

        pass.setText(userHelp.getPassword());
        repass.setText(userHelp.getPassword());
        name.setText(userHelp.getName());

        System.out.println(userHelp.getGender());

        if(userHelp.getGender().equalsIgnoreCase("M"))
        {
            male.setChecked(true);
        }

        else if(userHelp.getGender().equalsIgnoreCase("F"))
        {
            female.setChecked(true);
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateProfile.this, ListCategory.class);
                intent.putExtra("emailUser", emailUser);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pass.length() < 5 || repass.length() < 5)
                {
                    Toast.makeText(updateProfile.this, "Password length must be at least 5 (five) characters", Toast.LENGTH_LONG).show();
                }

                else
                {
                    //Checking pass Match or Not
                    if(pass.getText().toString().equals(repass.getText().toString()) &&
                            repass.getText().toString().equals(pass.getText().toString()))
                    {


                        //here
                        String gender;
                        if(male.isChecked())
                        {
                            gender = "M";
                        }

                        else
                        {
                            gender = "F";
                        }

                        User user = new User(email.getText().toString(),
                                pass.getText().toString(),
                                name.getText().toString(),gender);

                        System.out.println(email.getText().toString());
                        System.out.println( pass.getText().toString());
                        System.out.println(name.getText().toString());
                        System.out.println(gender);

                        updateAlert(user);




                    }

                    else
                    {
                        Toast.makeText(updateProfile.this, "Password does not match the confirm password", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // do something
            Intent intent = new Intent(updateProfile.this, ListCategory.class);
            intent.putExtra("emailUser", emailUser);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void updateAlert(final User userTemp) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEUTRAL:
                        //tombol dismiss
                        break;

                    case DialogInterface.BUTTON_POSITIVE:
                        dbUser.UpdateDataUser(userTemp);

                        Intent intent = new Intent(updateProfile.this, ListCategory.class);
                        intent.putExtra("emailUser", userTemp.getEmail());
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();

                        Toast.makeText(updateProfile.this, "Success", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to Update ?").setTitle("Confirm Update").setNeutralButton("CANCEL", dialogClickListener).setPositiveButton("UPDATE",dialogClickListener).show();
    }
}
