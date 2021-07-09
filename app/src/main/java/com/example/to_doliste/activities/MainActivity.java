package com.example.to_doliste.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.to_doliste.R;
import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private AufgabeRoomDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db = AufgabeRoomDatabase.getInstance(this);
        if(db.getRoomDao().getAll().size() > 0){
            findViewById(R.id.keineAufgaben).setVisibility(View.GONE);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        FloatingActionButton b = findViewById(R.id.main_Create);
        b.setOnClickListener(viewOnClickListener -> {
            Intent myIntent = new Intent(viewOnClickListener.getContext(), CreateActivity.class);
            startActivity(myIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        addAufgabenToClickableList();
    }

    private void addAufgabenToClickableList(){
        if (db != null){
            ListView aufgaben = findViewById(R.id.auftraege);
            ArrayAdapter<AufgabeData> aufgabeAdapter =  new ArrayAdapter<>(getApplicationContext(), R.layout.list_item);
            aufgabeAdapter.addAll(db.getRoomDao().getAll());
            aufgaben.setAdapter(aufgabeAdapter);
            AdapterView.OnItemClickListener mListClickedHandler = (parent, adapterViewOnClickListener, position, id) -> {
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                AufgabeData selected = (AufgabeData) parent.getItemAtPosition(position);
                intent.putExtra("aufgabeId", selected.getId());
                intent.putExtra("aufgabeTitel", selected.getTitel());
                intent.putExtra("aufgabeDatum", selected.getDatum());
                intent.putExtra("aufgabeBeschreibung", selected.getBeschreibung());
                startActivity(intent);
            };
            aufgaben.setOnItemClickListener(mListClickedHandler);
        }

    }
}