package com.example.to_doliste;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class updateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Aufgabe bearbeiten");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Button speichern = findViewById(R.id.update_btnSpeichern);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), updateActivity.class);
                startActivity(myIntent);
            }
        });

        Button verwerfen = findViewById(R.id.update_btnVerwerfen);
        verwerfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), updateActivity.class);
                startActivity(myIntent);
            }
        });
    }
}