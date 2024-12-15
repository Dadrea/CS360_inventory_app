package com.rmartin.cs360_inventory;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

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

        // Set up Logout button
        Button logoutButton = findViewById(R.id.LogOut_btn);
        logoutButton.setOnClickListener(v -> handleLogout());
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

    private void handleLogout() {
        // Clear user session or authentication data
        getSharedPreferences("user_session", MODE_PRIVATE).edit().clear().apply();

        // Show a Toast message for successful logout
        Toast.makeText(this, "You have been successfully logged out.", Toast.LENGTH_SHORT).show();

        // Navigate to MainActivity
        Intent intent = new Intent(InventoryActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        finish(); // Close the current activity
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchInventoryItems();  // Reload the inventory list after returning from AddItemActivity
    }
}
