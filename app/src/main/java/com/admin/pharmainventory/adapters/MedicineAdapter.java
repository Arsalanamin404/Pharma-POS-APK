package com.admin.pharmainventory.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.pharmainventory.MedicineDetailActivity;
import com.admin.pharmainventory.R;
import com.admin.pharmainventory.models.Medicine;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {
    private List<Medicine> medicineList;

    public MedicineAdapter(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    // view holder represents one row
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView brand_name, generic_name, quantity, cost_price, mrp, expiry;

        public ViewHolder(View itemView) {
            super(itemView);

            brand_name = itemView.findViewById(R.id.tvBrandName);
            generic_name = itemView.findViewById(R.id.tvGeneric);
            mrp = itemView.findViewById(R.id.tvMrp);
            cost_price = itemView.findViewById(R.id.tvCost);
            expiry = itemView.findViewById(R.id.tvExpiry);
            quantity = itemView.findViewById(R.id.tvQuantity);
        }
    }


    //  It creates the UI for each row/card only when needed.
    //  it is used to create item layouts efficiently for RecyclerView (for performance + reuse).
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicine, parent, false);
        return new ViewHolder(view);
    }

//    It puts data into the view created by onCreateViewHolder().
    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);

//        passing data from adapter to medicine detail page
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MedicineDetailActivity.class);

                intent.putExtra("brand_name",medicine.getBrand_name());
                intent.putExtra("generic_name",medicine.getGeneric_name());
                intent.putExtra("minStockLevel",medicine.getMinStockLevel());
                intent.putExtra("mrp", medicine.getMrp());
                intent.putExtra("expiry", medicine.getExpiry());
                intent.putExtra("cost_price" ,medicine.getCostPrice());
                intent.putExtra("quantity",medicine.getQuantity());
                intent.putExtra("description",medicine.getDescription());
                intent.putExtra("category", medicine.getCategory());
                intent.putExtra("batchNumber",medicine.getBatchNumber());
                intent.putExtra("prescriptionRequired",medicine.isPrescriptionRequired());
                intent.putExtra("stock_status",medicine.getStockStatus());
                intent.putExtra("manufacturer",medicine.getManufacturer());

                v.getContext().startActivity(intent);
            }
        });

        holder.brand_name.setText(medicine.getBrand_name());
        holder.generic_name.setText(medicine.getGeneric_name());
        holder.mrp.setText(String.valueOf("MRP: ₹" +medicine.getMrp()));
        holder.expiry.setText(String.valueOf("Expiry: "+ medicine.getExpiry()));
        holder.cost_price.setText(String.valueOf("Cost: ₹" +medicine.getCostPrice()));
        holder.quantity.setText(String.valueOf(medicine.getQuantity()));
    }

    @Override
//    return total item count
    public int getItemCount() {
        return medicineList.size();
    }

    public void updateList(List<Medicine> newList) {
        this.medicineList = newList;
        notifyDataSetChanged();
    }
}
