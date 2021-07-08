package com.example.to_doliste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private AufgabeRoomDatabase db = null;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AufgabeRoomDatabase.getInstance(this);
        addAufgabenToClickableList();
        MainActivity.context = getApplicationContext();
        if(db.getRoomDao().getAll().size() > 0){
            findViewById(R.id.keineAufgaben).setVisibility(View.GONE);
        }

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);



        int nightModeFlags = getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            break;

            case Configuration.UI_MODE_NIGHT_NO:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            break;
        }

    }


    public static Context getContext() {
            return MainActivity.context;
        }


    @Override
    protected void onResume() {
        super.onResume();
        FloatingActionButton b = findViewById(R.id.main_Create);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CreateActivity.class);
                startActivity(myIntent);
                addAufgabenToClickableList();
            }
        });
    }
    private void addAufgabenToClickableList(){
        if (db == null){

        }
        else {
            ListView aufgaben =  findViewById(R.id.auftraege);
            ArrayAdapter<AufgabeData> aufgabeAdapter =  new ArrayAdapter<>(getApplicationContext(), R.layout.list_item);
            aufgabeAdapter.addAll(db.getRoomDao().getAll());
            aufgaben.setAdapter(aufgabeAdapter);
            AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener()
            {
                public void onItemClick(AdapterView parent, View v, int position, long id)
                {
                    Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                    AufgabeData selected = (AufgabeData) parent.getItemAtPosition(position);
                    intent.putExtra("aufgabeId", selected.getId());
                    intent.putExtra("aufgabeTitel", selected.getTitel());
                    intent.putExtra("aufgabeDatum", selected.getDatum());
                    intent.putExtra("aufgabeBeschreibung", selected.getBeschreibung());
                    startActivity(intent);
                }

            };
            aufgaben.setOnItemClickListener(mListClickedHandler);
        }

    }
}