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

public class CreateActivity extends AppCompatActivity {
    private final AufgabeData data = new AufgabeData();
    private AufgabeRoomDatabase db = null;
    private AlertDialog.Builder dialogBuilder;
    private final String regex = "^[0-3][0-9].([0][1-9]|[1][0-2]).[1-9][0-9]{3}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Aufgabe hinzufügen");
        db = AufgabeRoomDatabase.getInstance(this);
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
        Button speichern = findViewById(R.id.create_btnSpeichern);
        speichern.setOnClickListener(viewOnClickListener -> {
            Intent myIntent = new Intent(viewOnClickListener.getContext(), MainActivity.class);
            EditText etTitel = findViewById(R.id.create_etTitel);
            EditText etDatum = findViewById(R.id.create_etDatum);
            EditText etBeschreibung = findViewById(R.id.create_etBeschreibung);

            if (!etTitel.getText().toString().isEmpty() && !etDatum.getText().toString().isEmpty() && etDatum.getText().toString().matches(regex)){
                data.setTitel(etTitel.getText().toString());
                data.setDatum(etDatum.getText().toString());
                data.setBeschreibung(etBeschreibung.getText().toString());
                db.getRoomDao().insert(data);
                Toast.makeText(CreateActivity.this, "Deine Aufgabe wurde hinzugefügt", Toast.LENGTH_SHORT).show();
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
    }
}