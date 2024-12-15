package com.rmartin.cs360_inventory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView itemNameTextView;
    private TextView itemCountTextView;
    private TextView itemDescriptionTextView;
    private Button backToInventoryButton;
    private Button modifyItemButton;
    private Button deleteItemButton;
    private InventoryDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        itemNameTextView = findViewById(R.id.productName);
        itemCountTextView = findViewById(R.id.productCount);
        itemDescriptionTextView = findViewById(R.id.productDescription);
        backToInventoryButton = findViewById(R.id.backToInventoryButton);
        modifyItemButton = findViewById(R.id.modifyItemButton);
        deleteItemButton = findViewById(R.id.deleteItemButton);

        // Initialize the database handler
        dbHandler = new InventoryDBHandler(this);

        // Get data from the Intent
        String itemName = getIntent().getStringExtra("item_name");
        int itemCount = getIntent().getIntExtra("item_count", 0);
        String itemDescription = getIntent().getStringExtra("item_description");

        // Set data to TextViews
        itemNameTextView.setText(itemName);
        itemCountTextView.setText("Count: " + itemCount);
        itemDescriptionTextView.setText(itemDescription);

        // Set up "Back to Inventory" button click listener
        backToInventoryButton.setOnClickListener(v -> {
            // Navigate back to the InventoryActivity
            Intent intent = new Intent(DetailActivity.this, InventoryActivity.class);
            startActivity(intent);
            finish(); // Optionally finish this activity to prevent going back to it
        });

        // Set up "Modify Item" button click listener
        modifyItemButton.setOnClickListener(v -> {
            // Navigate to ModifyItemActivity and pass the current item data
            Intent intent = new Intent(DetailActivity.this, ModifyItemActivity.class);
            intent.putExtra("item_name", itemName);
            intent.putExtra("item_count", itemCount);
            intent.putExtra("item_description", itemDescription);
            startActivity(intent);
        });

        // Set up "Delete Item" button click listener
        deleteItemButton.setOnClickListener(v -> {
            dbHandler.deleteItem(itemName); // Call deleteItem from DBHandler
            Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show();

            // Navigate back to the InventoryActivity
            Intent intent = new Intent(DetailActivity.this, InventoryActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHandler.close(); // Close the database to avoid memory leaks
    }
}

