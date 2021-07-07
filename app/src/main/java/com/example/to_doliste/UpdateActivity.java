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

import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;

public class UpdateActivity extends AppCompatActivity {
    private int aufgabeId;
    AufgabeData data = new AufgabeData();
    AufgabeRoomDatabase db = null;
    EditText editTextTitel, editTextDatum, editTextBeschreibung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Aufgabe bearbeiten");
        db = AufgabeRoomDatabase.getInstance(this);
        Intent intent = getIntent();
        aufgabeId = intent.getIntExtra("aufgabeId", 1);
        String titel = intent.getStringExtra("aufgabeTitel");
        String datum = intent.getStringExtra("aufgabeDatum");
        String beschreibung = intent.getStringExtra("aufgabeBeschreibung");

        editTextTitel = (EditText)findViewById(R.id.update_etTitel);
        editTextDatum = (EditText)findViewById(R.id.update_etDatum);
        editTextBeschreibung = (EditText)findViewById(R.id.update_etBeschreibung);
        editTextTitel.setText(titel);
        editTextDatum.setText(datum);
        editTextBeschreibung.setText(beschreibung);
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
        Button speichern = findViewById(R.id.update_btnSpeichern);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                data.setId(aufgabeId);
                data.setTitel(editTextTitel.getText().toString());
                data.setDatum(editTextDatum.getText().toString());
                data.setBeschreibung(editTextBeschreibung.getText().toString());
                db.getRoomDao().update(data);
                startActivity(myIntent);
            }
        });

        Button loeschen = findViewById(R.id.update_btnLoeschen);
        loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                data.setId(aufgabeId);
                Log.d("id", aufgabeId + "");
                db.getRoomDao().delete(data);
                startActivity(myIntent);
            }
        });
        Button verwerfen = findViewById(R.id.update_btnVerwerfen);
        loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}