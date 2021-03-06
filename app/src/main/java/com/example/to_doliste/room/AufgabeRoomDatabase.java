package com.example.to_doliste.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;


@Database(
        entities = {AufgabeData.class},
        version = 1
)
public abstract class AufgabeRoomDatabase extends androidx.room.RoomDatabase {
    public static AufgabeRoomDatabase INSTANCE = null;
    public static AufgabeData data;
    public static synchronized AufgabeRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            // Datenbank wird erstellt wenn keine vorhanden
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AufgabeRoomDatabase.class, "Database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract AufgabeDao getRoomDao();
}
