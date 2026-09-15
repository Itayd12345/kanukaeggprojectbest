package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView tv_title;
TextView tv_helpme;
TextView tv_count;
ImageButton imgbtn_egg;
Button btn_reset;
int counttvcount = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_count = findViewById(R.id.tv_count);
        imgbtn_egg = findViewById(R.id.imgbtn_egg);
        btn_reset = findViewById(R.id.btn_reset);
        imgbtn_egg.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        tv_count.setText(String.valueOf(counttvcount));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_title), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onClick(View v)
    {
        if(v == imgbtn_egg && counttvcount > 0)
        {
            counttvcount--;
            tv_count.setText(String.valueOf(counttvcount));
        }
        if(v == btn_reset)
        {
            counttvcount = 10;
            tv_count.setText(String.valueOf(counttvcount));
        }
    }
}