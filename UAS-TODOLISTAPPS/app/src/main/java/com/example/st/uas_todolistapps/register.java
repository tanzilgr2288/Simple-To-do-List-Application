package com.example.st.uas_todolistapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private Toolbar toolbar;
    private Button submit,cancel;
    private EditText email,pass,repass,name;
    private RadioButton male,female;
    final private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private dbHandlerUser dbUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);


        //Start
        submit = (Button) findViewById(R.id.submitRegister);
        cancel = (Button) findViewById(R.id.cancelRegister);

        email = (EditText) findViewById(R.id.emailRegister);
        pass = (EditText) findViewById(R.id.passwordRegister);
        repass = (EditText) findViewById(R.id.repasswordRegister);
        name = (EditText) findViewById(R.id.fullNameRegister);

        male = (RadioButton) findViewById(R.id.maleRB);
        female = (RadioButton) findViewById(R.id.femaleRB);

        dbUser = new dbHandlerUser(this);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking empty or not
                if(email.getText().toString().equalsIgnoreCase("") ||
                        pass.getText().toString().equalsIgnoreCase("") ||
                        repass.getText().toString().equalsIgnoreCase("") ||
                        name.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(register.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                }

                else
                {
                    //checking email input
                    String emailCheck = email.getText().toString().trim();


                    if (emailCheck.matches(emailPattern) && email.length() > 0)
                    {
                        //Checking Password Length
                        if(pass.length() < 5 || repass.length() < 5)
                        {
                            Toast.makeText(register.this, "Password length must be at least 5 (five) characters", Toast.LENGTH_LONG).show();
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

                                if(dbUser.checkingUser(user))
                                {
                                    Toast.makeText(register.this, "Email already exists", Toast.LENGTH_LONG).show();
                                }

                                else
                                {
                                    dbUser.adduser(user);

                                    finish();

                                    Toast.makeText(register.this, "Success", Toast.LENGTH_LONG).show();
                                }
                            }

                            else
                            {
                                Toast.makeText(register.this, "Password does not match the confirm password", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    else
                    {
                        Toast.makeText(register.this, "Email Invalid", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}
