package com.example.st.uas_todolistapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private Toolbar toolbar;
    private Button register,login;
    private EditText email,password;
    private dbHandlerUser dbUser ;
    final private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);


        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);


        //Start
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        email = (EditText) findViewById(R.id.emailLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        dbUser = new dbHandlerUser(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equalsIgnoreCase("") ||
                        password.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(login.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
                }

                else
                {
                    String emailCheck = email.getText().toString().trim();


                    if (emailCheck.matches(emailPattern) && email.length() > 0)
                    {
                        //checking login database
                        User user = new User(email.getText().toString(),
                                password.getText().toString(),"","");

                        if(dbUser.loginUser(user))
                        {
                            Toast.makeText(login.this, "Success", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(login.this, ListCategory.class);
                            intent.putExtra("emailUser", email.getText().toString());
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }

                        else
                        {
                            Toast.makeText(login.this, "Invalid email dan password", Toast.LENGTH_LONG).show();
                        }
                    }

                    else
                    {
                        Toast.makeText(login.this, "Email Invalid", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });


    }


}
