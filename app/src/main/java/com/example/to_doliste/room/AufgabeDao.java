package com.example.to_doliste.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AufgabeDao {
    @Query("SELECT * FROM AufgabeData")
    List<AufgabeData> getAll();

    @Insert
    void insert(AufgabeData data);

    @Update
    void update(AufgabeData data);

    @Delete
    void delete(AufgabeData data);
}
