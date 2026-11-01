package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class loginpage extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login;
    private EditText et_entername;
    private EditText et_enterpassword;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);
        btn_login = findViewById(R.id.btn_login);
        et_entername = findViewById(R.id.et_entername);
        et_enterpassword = findViewById(R.id.et_enterpassword);
        dbHelper = new DatabaseHelper(this);
        btn_login.setOnClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onClick(View v)
    {
        String name = et_entername.getText().toString();
        String password = et_enterpassword.getText().toString();
        if(v == btn_login){
            if (name.isEmpty() || password.isEmpty())
                Toast.makeText(this, "please enter both name and passowrd",
                        Toast.LENGTH_LONG).show();
            else
            {
                if (dbHelper.existPlayer(name))
                {
                    if(dbHelper.checkPassword(name, password))
                    {
                        et_entername.setText("");
                        et_enterpassword.setText("");
                        Intent intent = new Intent(loginpage.this, MainActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(loginpage.this, "password incorrect",
                                Toast.LENGTH_LONG).show();
                        et_enterpassword.setText("");
                    }
                }
                else
                {
                    dbHelper.addPlayer(name, password, 0);
                    et_entername.setText("");
                    et_enterpassword.setText("");
                    Intent intent = new Intent(loginpage.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        }
    }
}