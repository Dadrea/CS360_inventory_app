package com.rmartin.cs360_inventory;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.database.Cursor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import com.rmartin.cs360_inventory.RecyclerView.InventoryAdapter;
import com.rmartin.cs360_inventory.InventoryItem;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView inventoryRecyclerView;
    private InventoryAdapter inventoryAdapter;
    private InventoryDBHandler dbHandler;
    private List<InventoryItem> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory);

        // Apply edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.inventory), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up Spinner
        Spinner settingsSpinner = findViewById(R.id.settingsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.settings_items, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        settingsSpinner.setAdapter(adapter);

        // Initialize database handler
        dbHandler = new InventoryDBHandler(this);

        // Initialize RecyclerView
        inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);
        inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch inventory items and set up RecyclerView adapter
        fetchInventoryItems();

        // Set up Add Item button
        Button addItemButton = findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(v -> {
            // Navigate to AddNewItemActivity when the button is clicked
            Intent intent = new Intent(InventoryActivity.this, AddItemActivity.class);
            startActivity(intent);
        });
    }

    private void fetchInventoryItems() {
        inventoryList = new ArrayList<>();

        // Fetch data from SQLite database, ordered by item name
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT itemname, itemcount, itemdescription FROM inventory ORDER BY itemname ASC", null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int count = cursor.getInt(1);
                String description = cursor.getString(2);

                // Add items to the list
                inventoryList.add(new InventoryItem(name, count, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Set up adapter and assign to RecyclerView
        inventoryAdapter = new InventoryAdapter(this, inventoryList);
        inventoryRecyclerView.setAdapter(inventoryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchInventoryItems();  // Reload the inventory list after returning from AddItemActivity
    }
}
