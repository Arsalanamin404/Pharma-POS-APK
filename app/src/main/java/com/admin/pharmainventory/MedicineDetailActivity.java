package com.admin.pharmainventory;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.admin.pharmainventory.database.AppDatabase;

import java.util.Objects;


public class MedicineDetailActivity extends AppCompatActivity {

    TextView brand_name, generic_name, quantity;
    TextView mrp, cost_price, expiry;
    TextView description, category, stockBadge, minStock;
    TextView manufacturer, batch_no, prescription;

    Button btnDelete, btnEdit;

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        brand_name = findViewById(R.id.brand_name);
        generic_name = findViewById(R.id.generic_name);
        quantity = findViewById(R.id.quantity);
        mrp = findViewById(R.id.mrp);
        cost_price = findViewById(R.id.costPrice);
        expiry = findViewById(R.id.expiry);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        stockBadge = findViewById(R.id.stockBadge);
        minStock = findViewById(R.id.minStock);
        manufacturer = findViewById(R.id.manufacturer);
        batch_no = findViewById(R.id.batch_no);
        prescription = findViewById(R.id.prescription);

        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);

        appDatabase = AppDatabase.getInstance(this);


        Intent intent = getIntent();

        brand_name.setText(intent.getStringExtra("brand_name"));
        generic_name.setText(intent.getStringExtra("generic_name"));
        expiry.setText(intent.getStringExtra("expiry"));
        description.setText(intent.getStringExtra("description"));
        category.setText(intent.getStringExtra("category"));
        stockBadge.setText(intent.getStringExtra("stock_status"));
        manufacturer.setText(intent.getStringExtra("manufacturer"));
        batch_no.setText(intent.getStringExtra("batchNumber"));

        mrp.setText("₹" + intent.getDoubleExtra("mrp", 0));
        cost_price.setText("₹" + intent.getDoubleExtra("cost_price", 0));


        quantity.setText(intent.getIntExtra("quantity", 0) + " units");
        minStock.setText(String.valueOf(intent.getIntExtra("minStockLevel", 0)));


        boolean isPrescription = intent.getBooleanExtra("prescriptionRequired", false);
        prescription.setText(isPrescription ? "Required" : "Not Required");

        String stock_status = intent.getStringExtra("stock_status");


        if (Objects.equals(stock_status, "OUT_OF_STOCK")) {
            stockBadge.setBackgroundColor(Color.parseColor("#D32F2F"));
            stockBadge.setTextColor(Color.WHITE);

        } else if (Objects.equals(stock_status, "LOW_STOCK")) {
            stockBadge.setBackgroundColor(Color.parseColor("#FFA000"));
            stockBadge.setTextColor(Color.BLACK);
        } else {
            stockBadge.setBackgroundColor(Color.parseColor("#43A047"));
            stockBadge.setTextColor(Color.WHITE);
        }

        btnDelete.setOnClickListener(v -> {
            int id = intent.getIntExtra("id", 0);

            new AlertDialog.Builder(MedicineDetailActivity.this)
                    .setTitle("Delete Medicine")
                    .setMessage("Are you sure you want to delete this medicine?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        appDatabase.medicineDao().deleteById(id);

                        Toast.makeText(MedicineDetailActivity.this,
                                "Medicine deleted successfully",
                                Toast.LENGTH_SHORT).show();

                        setResult(RESULT_OK);
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(MedicineDetailActivity.this, AddMedicineActivity.class);

            editIntent.putExtra("isEdit", true);
            editIntent.putExtra("id", intent.getIntExtra("id", 0));

            editIntent.putExtra("brand_name", intent.getStringExtra("brand_name"));
            editIntent.putExtra("generic_name", intent.getStringExtra("generic_name"));
            editIntent.putExtra("category", intent.getStringExtra("category"));
            editIntent.putExtra("description", intent.getStringExtra("description"));
            editIntent.putExtra("quantity", intent.getIntExtra("quantity", 0));
            editIntent.putExtra("minStockLevel", intent.getIntExtra("minStockLevel", 0));
            editIntent.putExtra("mrp", intent.getDoubleExtra("mrp", 0));
            editIntent.putExtra("cost_price", intent.getDoubleExtra("cost_price", 0));
            editIntent.putExtra("expiry", intent.getStringExtra("expiry"));
            editIntent.putExtra("manufacturer", intent.getStringExtra("manufacturer"));
            editIntent.putExtra("batchNumber", intent.getStringExtra("batchNumber"));
            editIntent.putExtra("prescriptionRequired", intent.getBooleanExtra("prescriptionRequired", false));

            startActivity(editIntent);
        });

    }
}