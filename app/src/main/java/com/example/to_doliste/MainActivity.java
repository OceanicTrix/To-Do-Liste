package com.example.to_doliste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    AufgabeRoomDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AufgabeRoomDatabase.getInstance(this);
        Log.d("tag", db.getRoomDao().getAll().toString());
        addAufgabenToClickableList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FloatingActionButton b = findViewById(R.id.floating_action_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CreateActivity.class);
                startActivity(myIntent);
            }
        });
    }
    private void addAufgabenToClickableList(){
        if (db == null){

        }
        else {
            ListView aufgaben =  findViewById(R.id.auftraege);
            ArrayAdapter<AufgabeData> aufgabeAdapter =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
            aufgabeAdapter.addAll(db.getRoomDao().getAll());
            aufgaben.setAdapter(aufgabeAdapter);
        }

    }
}