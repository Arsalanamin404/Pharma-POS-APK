package com.admin.pharmainventory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.admin.pharmainventory.mock.MockData;
import com.admin.pharmainventory.models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    final private static String DATABASE_NAME = "pharma_pos.db";
    final  private static String TABLE_NAME = "products";
    final  private static int VERSION = 3;
    public static final String COL_ID = "id";
    public static final String COL_GENERIC = "generic_name";
    public static final String COL_BRAND = "brand_name";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_MIN_STOCK = "min_stock";
    public static final String COL_COST = "cost_price";
    public static final String COL_MRP = "mrp";
    public static final String COL_EXPIRY = "expiry_date";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_CATEGORY = "category";
    public static final String COL_MANUFACTURER = "manufacturer";
    public static final String COL_BATCH = "batch_number";
    public static final String COL_PRESCRIPTION = "prescription_required";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_GENERIC + " TEXT NOT NULL, "
                + COL_BRAND + " TEXT NOT NULL, "
                + COL_QUANTITY + " INTEGER NOT NULL CHECK(" + COL_QUANTITY + " >= 0), "
                + COL_MIN_STOCK + " INTEGER NOT NULL CHECK(" + COL_MIN_STOCK + " >= 0), "
                + COL_COST + " REAL NOT NULL CHECK(" + COL_COST + " >= 0), "
                + COL_MRP + " REAL NOT NULL CHECK(" + COL_MRP + " >= " + COL_COST + "), "
                + COL_EXPIRY + " TEXT NOT NULL, "
                + COL_DESCRIPTION + " TEXT, "
                + COL_CATEGORY + " TEXT NOT NULL, "
                + COL_MANUFACTURER + " TEXT NOT NULL, "
                + COL_BATCH + " TEXT NOT NULL, "
                + COL_PRESCRIPTION + " INTEGER NOT NULL CHECK(" + COL_PRESCRIPTION + " IN (0,1))"
                + ")";
        db.execSQL(CREATE_TABLE);
        insertMockData(db);
    }


    public  boolean insertProduct(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_GENERIC, medicine.getGeneric_name());
        values.put(COL_BRAND, medicine.getBrand_name());
        values.put(COL_QUANTITY, medicine.getQuantity());
        values.put(COL_MIN_STOCK, medicine.getMinStockLevel());
        values.put(COL_COST, medicine.getCostPrice());
        values.put(COL_MRP, medicine.getMrp());
        values.put(COL_EXPIRY, medicine.getExpiry());
        values.put(COL_DESCRIPTION, medicine.getDescription());
        values.put(COL_CATEGORY, medicine.getCategory());
        values.put(COL_MANUFACTURER, medicine.getManufacturer());
        values.put(COL_BATCH, medicine.getBatchNumber());
        values.put(COL_PRESCRIPTION, medicine.isPrescriptionRequired() ? 1 : 0);

        long result = db.insert(TABLE_NAME,null,values);

        db.close();
        return result !=-1;
    }

    public List<Medicine> getAllProducts() {
        List<Medicine> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Medicine m = new Medicine();
                m.setGeneric_name(cursor.getString(cursor.getColumnIndexOrThrow(COL_GENERIC)));
                m.setBrand_name(cursor.getString(cursor.getColumnIndexOrThrow(COL_BRAND)));
                m.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COL_QUANTITY)));
                m.setMinStockLevel(cursor.getInt(cursor.getColumnIndexOrThrow(COL_MIN_STOCK)));
                m.setCostPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COL_COST)));
                m.setMrp(cursor.getDouble(cursor.getColumnIndexOrThrow(COL_MRP)));
                m.setExpiry(cursor.getString(cursor.getColumnIndexOrThrow(COL_EXPIRY)));
                m.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPTION)));
                m.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(COL_CATEGORY)));
                m.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(COL_MANUFACTURER)));
                m.setBatchNumber(cursor.getString(cursor.getColumnIndexOrThrow(COL_BATCH)));
                m.setPrescriptionRequired(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_PRESCRIPTION)) == 1
                );

                list.add(m);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertMockData(SQLiteDatabase db) {
        List<Medicine> list = MockData.getMedicines();

        for (Medicine medicine : list) {

            ContentValues values = new ContentValues();
            values.put(COL_GENERIC, medicine.getGeneric_name());
            values.put(COL_BRAND, medicine.getBrand_name());
            values.put(COL_QUANTITY, medicine.getQuantity());
            values.put(COL_MIN_STOCK, medicine.getMinStockLevel());
            values.put(COL_COST, medicine.getCostPrice());
            values.put(COL_MRP, medicine.getMrp());
            values.put(COL_EXPIRY, medicine.getExpiry());
            values.put(COL_DESCRIPTION, medicine.getDescription());
            values.put(COL_CATEGORY, medicine.getCategory());
            values.put(COL_MANUFACTURER, medicine.getManufacturer());
            values.put(COL_BATCH, medicine.getBatchNumber());
            values.put(COL_PRESCRIPTION, medicine.isPrescriptionRequired() ? 1 : 0);

            db.insert(TABLE_NAME, null, values);
        }
    }
}
