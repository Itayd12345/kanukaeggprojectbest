package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
public class activity_players_board extends AppCompatActivity implements
        View.OnClickListener {
    // declare visible variables:
    private RecyclerView rv_players;
    private Button btn_back;
    // variables related to DB helper and adapter:
    private DatabaseHelper dbHelper;
    private ArrayList<Player> players;
    private adapter ea;
    private Button btn_delete;
    private Boolean deletetime = false;
    // initiate the screen:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_players_board);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pb_main), (v,
                                                                               insets) -> {
            Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                    systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        rv_players = (RecyclerView) findViewById(R.id.rv_players);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_delete = findViewById(R.id.btn_delete);
        dbHelper = new DatabaseHelper(this);
        players = dbHelper.getPlayersInfo();
        Collections.sort(players);
        ea = new adapter(players);
        this.btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        rv_players.setAdapter(ea);
        rv_players.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onClick(View view)
    {
        if (view == btn_back)
        {
            finish();
        }
        if (view == btn_delete && deletetime == false) {
            EditText input = new EditText(this);

            new AlertDialog.Builder(this)
                    .setView(input)
                    .setPositiveButton("Delete", (d, w) ->
                            dbHelper.removePlayer(input.getText().toString()))
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }
}