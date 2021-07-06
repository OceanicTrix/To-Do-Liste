package com.example.to_doliste.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {AufgabeData.class},
        version = 1
)
public abstract class AufgabeRoomDatabase extends androidx.room.RoomDatabase {
    public static RoomDatabase INSTANCE = null;
    public static synchronized AufgabeRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AufgabeRoomDatabase.class, "Database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract AufgabeDao getRoomDao();

}
