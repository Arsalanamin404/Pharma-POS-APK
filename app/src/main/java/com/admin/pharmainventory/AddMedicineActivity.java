package com.admin.pharmainventory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.admin.pharmainventory.database.AppDatabase;
import com.admin.pharmainventory.entities.MedicineEntity;
import com.google.android.material.button.MaterialButton;

public class AddMedicineActivity extends AppCompatActivity {

    boolean isEdit = false;
    int medicineId = -1;

    EditText et_brand, et_generic, et_category, et_description;
    EditText et_quantity, et_min_stock, et_manufacturer, et_batch;
    EditText et_expiry, et_cost, et_mrp;
    CheckBox isPrescriptionRequired;

    Button btnSave;
    MaterialButton btnUploadExcelFile;

    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_medicine);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        et_brand = findViewById(R.id.et_brand);
        et_generic = findViewById(R.id.et_generic);
        et_category = findViewById(R.id.et_category);
        et_description = findViewById(R.id.et_description);
        et_quantity = findViewById(R.id.et_quantity);
        et_min_stock = findViewById(R.id.et_min_stock);
        et_batch = findViewById(R.id.et_batch);
        et_expiry = findViewById(R.id.et_expiry);
        et_cost = findViewById(R.id.et_cost);
        et_mrp = findViewById(R.id.et_mrp);
        et_manufacturer = findViewById(R.id.et_manufacturer);
        isPrescriptionRequired = findViewById(R.id.cb_isPrescriptionRequired);

        btnSave = findViewById(R.id.btnSave);
        btnUploadExcelFile = findViewById(R.id.btnUploadExcel);

        database = AppDatabase.getInstance(this);


        isEdit = getIntent().getBooleanExtra("isEdit", false);
        medicineId = getIntent().getIntExtra("id", -1);


        if (isEdit) {
            et_brand.setText(getIntent().getStringExtra("brand_name"));
            et_generic.setText(getIntent().getStringExtra("generic_name"));
            et_category.setText(getIntent().getStringExtra("category"));
            et_description.setText(getIntent().getStringExtra("description"));
            et_quantity.setText(String.valueOf(getIntent().getIntExtra("quantity", 0)));
            et_min_stock.setText(String.valueOf(getIntent().getIntExtra("minStockLevel", 0)));
            et_manufacturer.setText(getIntent().getStringExtra("manufacturer"));
            et_batch.setText(getIntent().getStringExtra("batchNumber"));
            et_expiry.setText(getIntent().getStringExtra("expiry"));
            et_cost.setText(String.valueOf(getIntent().getDoubleExtra("cost_price", 0)));
            et_mrp.setText(String.valueOf(getIntent().getDoubleExtra("mrp", 0)));
            isPrescriptionRequired.setChecked(getIntent().getBooleanExtra("prescriptionRequired", false));

            btnSave.setText("Update Medicine");
        }


        btnSave.setOnClickListener(v -> {

            String brand = et_brand.getText().toString().trim();
            String generic = et_generic.getText().toString().trim();
            String category = et_category.getText().toString().trim();
            String description = et_description.getText().toString().trim();
            String quantity = et_quantity.getText().toString().trim();
            String minStock = et_min_stock.getText().toString().trim();
            String manufacturer = et_manufacturer.getText().toString().trim();
            String batch = et_batch.getText().toString().trim();
            String expiry = et_expiry.getText().toString().trim();
            String cost = et_cost.getText().toString().trim();
            String mrp = et_mrp.getText().toString().trim();


            if (brand.isEmpty()) { et_brand.setError("Required"); return; }
            if (generic.isEmpty()) { et_generic.setError("Required"); return; }
            if (category.isEmpty()) { et_category.setError("Required"); return; }
            if (quantity.isEmpty()) { et_quantity.setError("Required"); return; }
            if (minStock.isEmpty()) { et_min_stock.setError("Required"); return; }
            if (manufacturer.isEmpty()) { et_manufacturer.setError("Required"); return; }
            if (batch.isEmpty()) { et_batch.setError("Required"); return; }
            if (expiry.isEmpty()) { et_expiry.setError("Required"); return; }
            if (cost.isEmpty()) { et_cost.setError("Required"); return; }
            if (mrp.isEmpty()) { et_mrp.setError("Required"); return; }

            try {
                int qty = Integer.parseInt(quantity);
                int minStockLevel = Integer.parseInt(minStock);
                double costPrice = Double.parseDouble(cost);
                double mrpPrice = Double.parseDouble(mrp);


                if (qty < 0 || minStockLevel < 0) {
                    Toast.makeText(this, "Invalid values", Toast.LENGTH_SHORT).show();
                    return;
                }

                MedicineEntity medicine = new MedicineEntity();

                if (isEdit) {
                    medicine.setId(medicineId);
                }

                medicine.setGenericName(generic);
                medicine.setBrandName(brand);
                medicine.setQuantity(qty);
                medicine.setMinStockLevel(minStockLevel);
                medicine.setCostPrice(costPrice);
                medicine.setMrp(mrpPrice);
                medicine.setExpiry(expiry);
                medicine.setDescription(description);
                medicine.setCategory(category);
                medicine.setManufacturer(manufacturer);
                medicine.setBatchNumber(batch);
                medicine.setPrescriptionRequired(isPrescriptionRequired.isChecked());


                if (isEdit) {
                    database.medicineDao().update(medicine);
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    long id = database.medicineDao().insert(medicine);
                    if (id <= 0) {
                        Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK);
                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid numeric values", Toast.LENGTH_SHORT).show();
            }
            catch (IllegalArgumentException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}