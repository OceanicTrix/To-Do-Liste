package com.example.to_doliste.runner;

import android.app.Application;

import androidx.test.runner.AndroidJUnitRunner;

import com.example.to_doliste.room.AufgabeRoomDatabase;

public class CustomRunner extends AndroidJUnitRunner {
    AufgabeRoomDatabase db = null;
    @Override
    public void callApplicationOnCreate(Application app) {
        super.callApplicationOnCreate(app);
        db = AufgabeRoomDatabase.getInstance(app);
        db.clearAllTables();
    }

}
