package com.admin.pharmainventory.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.admin.pharmainventory.dao.MedicineDao;
import com.admin.pharmainventory.entities.MedicineEntity;

@Database(entities = {MedicineEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // this gives access to methods like insert,delete, fetch
    public abstract MedicineDao medicineDao();
}