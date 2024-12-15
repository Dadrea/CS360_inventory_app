package com.rmartin.cs360_inventory.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rmartin.cs360_inventory.InventoryItem;
import com.rmartin.cs360_inventory.R;
import com.rmartin.cs360_inventory.DetailActivity;
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

            // Set up the onClickListener for navigating to the detailed view
            holder.itemView.setOnClickListener(v -> {
                // Start the DetailActivity when an item is clicked
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("item_name", item.getName());
                intent.putExtra("item_count", item.getCount());
                intent.putExtra("item_description", item.getDescription());
                context.startActivity(intent);
            });
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