package com.example.to_doliste.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AufgabeData {

    public AufgabeData(String titel, String datum, String beschreibung) {
        this.titel = titel;
        this.datum = datum;
        this.beschreibung = beschreibung;
    }
    public AufgabeData(){};

    @PrimaryKey(autoGenerate = true )
    private int id;

    private String titel, datum, beschreibung;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return  getTitel() + "\n" + getDatum();
    }
}
