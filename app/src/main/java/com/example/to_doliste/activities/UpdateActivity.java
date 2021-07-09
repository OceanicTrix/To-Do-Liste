package com.example.to_doliste.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to_doliste.R;
import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;

public class UpdateActivity extends AppCompatActivity {
    private int aufgabeId;
    private final AufgabeData data = new AufgabeData();
    private AufgabeRoomDatabase db = null;
    private EditText etTitel, etDatum, etBeschreibung;
    private AlertDialog.Builder dialogBuilder;
    private final String regex = "^[0-3][0-9].([0][1-9]||[1][0-2]).[1-9][0-9]{3}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        speichern.setOnClickListener(viewOnClickListener -> {
            Intent myIntent = new Intent(viewOnClickListener.getContext(), MainActivity.class);
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
                dialogBuilder.setPositiveButton("Ok", (dialog, id) -> {

                });
                dialogBuilder.setMessage("Keine Angabe darf leer sein oder den Vorgaben nicht entsprechen").setTitle("Fehler");
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            }
        });

            Button loeschen = findViewById(R.id.update_btnLoeschen);
            loeschen.setOnClickListener(viewOnClickListener -> {
                Intent myIntent = new Intent(viewOnClickListener.getContext(), MainActivity.class);
                data.setId(aufgabeId);
                db.getRoomDao().delete(data);
                Toast.makeText(UpdateActivity.this, "Deine Aufgabe wurde gelöscht", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            });
            Button verwerfen = findViewById(R.id.update_btnVerwerfen);
            verwerfen.setOnClickListener(viewOnClickListener -> {
                Intent myIntent = new Intent(viewOnClickListener.getContext(), MainActivity.class);
                Toast.makeText(UpdateActivity.this, "Deine Änderung wurde verworfen", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            });
    }
}