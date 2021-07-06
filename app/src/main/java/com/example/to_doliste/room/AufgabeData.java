package com.example.to_doliste.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AufgabeData {

    public AufgabeData(String titel, String datum, String beschreibung) {
        this.titel = titel;
        this.datum = datum;
        this.beschreibung = beschreibung;
    }

    @PrimaryKey(autoGenerate = true )
    private int id;

    private String titel, datum, beschreibung;

}
