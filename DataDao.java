package com.example.finnishtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM resultTable")
    List<DataEntity> getAll();

    @Insert
    void insertAll(DataEntity... results);

    @Delete
    void delete(DataEntity resultTable);
}
