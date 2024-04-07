package com.example.finnishtest;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {DataEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataDao getDao();
    private static AppDatabase instance;

    static AppDatabase getAppDataBase(final Context context){
        if(instance == null){
            synchronized (AppDatabase.class){
                instance = Room.databaseBuilder(context, AppDatabase.class, "resultTable")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }
}
