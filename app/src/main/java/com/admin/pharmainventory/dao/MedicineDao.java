package com.admin.pharmainventory.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.admin.pharmainventory.entities.MedicineEntity;

import java.util.List;

//DAO = Data Access Object, it consists of all the business logic of the App
// All methods in interface are implicitly public abstract
@Dao
public interface MedicineDao {
    // Insert one medicine
    @Insert
    void insert(MedicineEntity medicine);

    // Get all medicines from Database
    @Query("SELECT * FROM medicines")
    List<MedicineEntity> getAllMedicines();


    // Delete All (for testing)
    @Query("DELETE FROM medicines")
    void delete();

}
