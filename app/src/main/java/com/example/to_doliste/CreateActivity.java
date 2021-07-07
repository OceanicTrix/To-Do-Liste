package com.example.to_doliste;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;

public class CreateActivity extends AppCompatActivity {
    AufgabeData data = new AufgabeData();
    AufgabeRoomDatabase db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Aufgabe hinzufügen");
        db = AufgabeRoomDatabase.getInstance(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Button speichern = findViewById(R.id.create_btnSpeichern);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                EditText editTextTitel = (EditText)findViewById(R.id.create_etTitel);
                EditText editTextDatum = (EditText)findViewById(R.id.create_etDatum);
                EditText editTextBeschreibung = (EditText)findViewById(R.id.create_etBeschreibung);
                data.setTitel(editTextTitel.getText().toString());
                data.setDatum(editTextDatum.getText().toString());
                data.setBeschreibung(editTextBeschreibung.getText().toString());
                db.getRoomDao().insert(data);
                Toast.makeText(CreateActivity.this, "Deine Aufgabe wurde hinzugefügt", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            }
        });

    }
}