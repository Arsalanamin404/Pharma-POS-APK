package com.admin.pharmainventory.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.admin.pharmainventory.dao.MedicineDao;
import com.admin.pharmainventory.entities.MedicineEntity;

@Database(entities = {MedicineEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // this gives access to methods like insert,delete, fetch
    public abstract MedicineDao medicineDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "pharma_db"
                    )
                    .allowMainThreadQueries() // only for testing
                    .build();
        }
        return instance;
    }
}