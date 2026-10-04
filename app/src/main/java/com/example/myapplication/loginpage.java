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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);
        btn_login = findViewById(R.id.btn_login);
        et_entername = findViewById(R.id.et_entername);
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
        String n = et_entername.getText().toString();
        if(n.isEmpty())
        {
            Toast.makeText(this, "please enter your name",
                    Toast.LENGTH_LONG).show();
        }
        if(v == btn_login){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", n);
            startActivity(intent);
        }
    }
}