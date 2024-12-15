package com.rmartin.cs360_inventory;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {
    private EditText itemNameInput, itemCountInput, itemDescriptionInput;
    private Button addItemButton, backToInventoryButton;
    private InventoryDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);

        // Initialize views
        itemNameInput = findViewById(R.id.new_item_input);
        itemCountInput = findViewById(R.id.new_item_count);
        itemDescriptionInput = findViewById(R.id.item_description);
        addItemButton = findViewById(R.id.create_item_btn);
        backToInventoryButton = findViewById(R.id.back_to_inv_btn);

        // Initialize database handler
        dbHandler = new InventoryDBHandler(this);

        // Set up button click listeners
        addItemButton.setOnClickListener(v -> addItemToDatabase());
        backToInventoryButton.setOnClickListener(v -> navigateBackToInventory());
    }

    // Method to add new item to the database
    private void addItemToDatabase() {
        String itemName = itemNameInput.getText().toString().trim();
        String itemCountStr = itemCountInput.getText().toString().trim();
        String itemDescription = itemDescriptionInput.getText().toString().trim();

        if (itemName.isEmpty() || itemCountStr.isEmpty() || itemDescription.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int itemCount = Integer.parseInt(itemCountStr);

            // Add item to database
            dbHandler.addNewItem(itemName, itemCount, itemDescription);
            Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();

            // Clear fields after adding
            itemNameInput.setText("");
            itemCountInput.setText("");
            itemDescriptionInput.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid item count", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to navigate back to InventoryActivity
    private void navigateBackToInventory() {
        Intent intent = new Intent(AddItemActivity.this, InventoryActivity.class);
        startActivity(intent);
        finish();  // Close current activity
    }
}
