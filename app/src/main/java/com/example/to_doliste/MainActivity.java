package com.example.to_doliste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    AufgabeRoomDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AufgabeRoomDatabase.getInstance(this);
        Log.d("tag", db.getRoomDao().getAll().toString());
        addAufgabenToClickableList();
        if(db.getRoomDao().getAll().size() < 1){
            TextView textView;

        }
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
            ArrayAdapter<AufgabeData> aufgabeAdapter =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
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