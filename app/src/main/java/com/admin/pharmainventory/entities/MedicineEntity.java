package com.admin.pharmainventory.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicines")
public class MedicineEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String genericName;
    public String brandName;
    public String category;
    public String description;
    public int quantity;
    public int minStockLevel;
    public double costPrice;
    public double mrp;
    public String expiry;
    public String batchNumber;
    public String manufacturer;
    public boolean prescriptionRequired;
}
