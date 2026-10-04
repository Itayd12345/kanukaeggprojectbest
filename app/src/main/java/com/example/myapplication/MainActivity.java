package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
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
Button btn_back;
Switch swtch_lightdark;
private EggManager em;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        em = new EggManager(this);
        Intent intent = getIntent();
        em.setName(intent.getStringExtra("name"));
        tv_count = findViewById(R.id.tv_count);
        imgbtn_egg = findViewById(R.id.imgbtn_egg);
        btn_reset = findViewById(R.id.btn_reset);
        btn_back = findViewById(R.id.btn_back);
        tv_helpme = findViewById(R.id.tv_helpme);
        swtch_lightdark = findViewById(R.id.swch_lightdark);
        tv_helpme.setText(em.getName() + " help me");
        imgbtn_egg.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        swtch_lightdark.setOnClickListener(this);
        tv_count.setText(String.valueOf(em.getCount()));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_title), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onClick(View v)
    {
        if(v == imgbtn_egg && !em.isItTime())
        {
            em.setCount(em.getCount() - 1);
            tv_count.setText(String.valueOf(em.getCount()));
        }
        if(v == btn_reset)
        {
            em.setCount(10);
            tv_count.setText(String.valueOf(em.getCount()));
            imgbtn_egg.setImageResource(R.drawable.niceegg);
        }
        if(em.isItTime())
            imgbtn_egg.setImageResource(R.drawable.hatchchicken);
        if (v == btn_back)
            finish();
        if (v == swtch_lightdark){
            if(swtch_lightdark.isChecked())
                imgbtn_egg.setAlpha(0.5f);
            else
                imgbtn_egg.setAlpha(1f);
        }
    }
}