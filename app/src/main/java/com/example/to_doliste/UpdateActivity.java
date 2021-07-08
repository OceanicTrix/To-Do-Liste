package com.example.to_doliste;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class UpdateActivity extends AppCompatActivity {
    private int aufgabeId;
    private AufgabeData data = new AufgabeData();
    private AufgabeRoomDatabase db = null;
    private EditText etTitel, etDatum, etBeschreibung;
    private AlertDialog.Builder dialogBuilder;
    private String regex = "^[0-3]{1}[0-9]{1}.([0]{1}[1-9]{1}||[1]{1}[0-2]).[1-9]{1}[0-9]{3}$";
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
        aufgabeId = intent.getIntExtra("aufgabeId", 0);
        String titel = intent.getStringExtra("aufgabeTitel");
        String datum = intent.getStringExtra("aufgabeDatum");
        String beschreibung = intent.getStringExtra("aufgabeBeschreibung");

        etTitel = findViewById(R.id.update_etTitel);
        etDatum = findViewById(R.id.update_etDatum);
        etBeschreibung = findViewById(R.id.update_etBeschreibung);
        etTitel.setText(titel);
        etDatum.setText(datum);
        etBeschreibung.setText(beschreibung);
        dialogBuilder = new AlertDialog.Builder(this);
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
                if (!etTitel.getText().toString().isEmpty() && !etDatum.getText().toString().isEmpty() && etDatum.getText().toString().matches(regex)) {
                    data.setId(aufgabeId);
                    data.setTitel(etTitel.getText().toString());
                    data.setDatum(etDatum.getText().toString());
                    data.setBeschreibung(etBeschreibung.getText().toString());
                    db.getRoomDao().update(data);
                    Toast.makeText(UpdateActivity.this, "Deine Aufgabe wurde geändert", Toast.LENGTH_SHORT).show();
                    startActivity(myIntent);
                }
                else{
                    dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    dialogBuilder.setMessage("Keine Angabe darf leer oder den Vorgaben nicht entsprechen").setTitle("Fehler");
                    AlertDialog dialog = dialogBuilder.create();
                    dialog.show();
                }
            }
        });

            Button loeschen = findViewById(R.id.update_btnLoeschen);
            loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                data.setId(aufgabeId);
                db.getRoomDao().delete(data);
                Toast.makeText(UpdateActivity.this, "Deine Aufgabe wurde gelöscht", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            }
            });
            Button verwerfen = findViewById(R.id.update_btnVerwerfen);
            verwerfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                Toast.makeText(UpdateActivity.this, "Deine Änderung wurde verworfen", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            }
            });
    }
}