package com.admin.pharmainventory.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.admin.pharmainventory.entities.MedicineEntity;

import java.util.List;

//DAO = Data Access Object, it consists of all the business logic of the App
// All methods in interface are implicitly public abstract
@Dao
public interface MedicineDao {
    // Insert one medicine
    @Insert
    long insert(MedicineEntity medicine);

    // Get all medicines from Database
    @Query("SELECT * FROM medicines ORDER BY brandName ASC")
    List<MedicineEntity> getAllMedicines();


    @Query("DELETE FROM medicines WHERE id = :id")
    void deleteById(int id);

    @Update
    void update(MedicineEntity medicine);

    @Query("UPDATE medicines SET quantity = :quantity WHERE id = :id")
    void updateQuantity(int id, int quantity);

    // Delete All (for testing)
    @Query("DELETE FROM medicines")
    void deleteAll();

}
