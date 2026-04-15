package com.admin.pharmainventory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.pharmainventory.adapters.MedicineAdapter;
import com.admin.pharmainventory.mock.MockData;
import com.admin.pharmainventory.models.Medicine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_medicine_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Find Recycler View
        recyclerView = findViewById(R.id.recyclerView);

        //2. Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //3. Create Data
        List<Medicine> medicineList = MockData.getMedicines();

        //4. Create Adapter
        MedicineAdapter adapter = new MedicineAdapter(medicineList);

        //5. Attach adapter to Recycler View
        recyclerView.setAdapter(adapter);

        add_medicine_btn = findViewById(R.id.addMedicineButton);
        add_medicine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMedicineActivity.class);
                startActivity(intent);
            }
        });
    }
}