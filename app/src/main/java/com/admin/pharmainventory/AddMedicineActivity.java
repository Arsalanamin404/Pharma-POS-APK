package com.admin.pharmainventory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.admin.pharmainventory.database.DBHandler;
import com.admin.pharmainventory.models.Medicine;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


public class AddMedicineActivity extends AppCompatActivity {

    EditText  et_brand, et_generic, et_category, et_description;
    EditText et_quantity, et_min_stock, et_manufacturer, et_batch;
    EditText et_expiry, et_cost, et_mrp;
    CheckBox isPrescriptionRequired;

    Button btnSave;
    MaterialButton btnUploadExcelFile;

    DBHandler dbHandler;

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

        dbHandler = new DBHandler(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                if (brand.isEmpty()) {
                    et_brand.setError("Brand name is required");
                    et_brand.requestFocus();
                    return;
                }

                if (generic.isEmpty()) {
                    et_generic.setError("Generic name is required");
                    return;
                }

                if (category.isEmpty()) {
                    et_category.setError("Drug category is required");
                    return;
                }

                if (quantity.isEmpty()) {
                    et_quantity.setError("Quantity is required");
                    return;
                }

                if (minStock.isEmpty()) {
                    et_min_stock.setError("Minimum stock level is required");
                    return;
                }

                if (manufacturer.isEmpty()) {
                    et_manufacturer.setError("Manufacturer name is required");
                    return;
                }

                if (batch.isEmpty()) {
                    et_batch.setError("Drug batch no. is required");
                    return;
                }

                if (expiry.isEmpty()) {
                    et_expiry.setError("Expiry date is required");
                    return;
                }

                if (cost.isEmpty()) {
                    et_cost.setError("Cost price is required");
                    return;
                }

                if (mrp.isEmpty()) {
                    et_mrp.setError("MRP is required");
                    return;
                }


                try {
                    int qty = Integer.parseInt(quantity);
                    int minStockLevel = Integer.parseInt(minStock);
                    double costPrice = Double.parseDouble(cost);
                    double mrpPrice = Double.parseDouble(mrp);

                    boolean cbIsReq = isPrescriptionRequired.isChecked();

                    Medicine medicine = new Medicine(
                            generic,
                            brand,
                            qty,
                            minStockLevel,
                            costPrice,
                            mrpPrice,
                            expiry,
                            description,
                            category,
                            manufacturer,
                            batch,
                            cbIsReq
                    );

                    boolean success = dbHandler.insertProduct(medicine);

                    if (success) {
                        Toast.makeText(getApplicationContext(), "Saved Successfully",Toast.LENGTH_LONG).show();
                        et_brand.setText("");
                        et_generic.setText("");
                        et_category.setText("");
                        et_description.setText("");
                        et_quantity.setText("");
                        et_min_stock.setText("");
                        et_manufacturer.setText("");
                        et_batch.setText("");
                        et_expiry.setText("");
                        et_cost.setText("");
                        et_mrp.setText("");
                        isPrescriptionRequired.setChecked(false);

                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Insert Failed (Check duplicate or constraints)",Toast.LENGTH_LONG).show();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid numeric values",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}