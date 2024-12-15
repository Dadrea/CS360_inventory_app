package com.rmartin.cs360_inventory.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rmartin.cs360_inventory.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rmartin.cs360_inventory.InventoryItem;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private final Context context;
    private final List<InventoryItem> inventoryList;

    public InventoryAdapter(Context context, List<InventoryItem> inventoryList) {
        this.context = context;
        this.inventoryList = inventoryList;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inventory_items, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        InventoryItem item = inventoryList.get(position);

        // Ensure the item data is valid before setting the text
        if (item != null) {
            holder.productName.setText(item.getName() != null ? item.getName() : "Unknown Product");
            holder.productCount.setText("Count: " + (item.getCount() >= 0 ? item.getCount() : 0));
        }
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productCount;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productCount = itemView.findViewById(R.id.productCount);
        }
    }
}