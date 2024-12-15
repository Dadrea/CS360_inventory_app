package com.rmartin.cs360_inventory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyItemActivity extends AppCompatActivity {

    private EditText countEditText, descriptionEditText;
    private Button saveButton;
    private String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_item);

        // Initialize views
        countEditText = findViewById(R.id.countEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        // Get item details from the Intent
        itemName = getIntent().getStringExtra("item_name");
        int currentCount = getIntent().getIntExtra("item_count", 0);
        String currentDescription = getIntent().getStringExtra("item_description");

        // Set the current values in the EditText fields
        countEditText.setText(String.valueOf(currentCount));
        descriptionEditText.setText(currentDescription);

        // Set up save button click listener
        saveButton.setOnClickListener(v -> {
            // Get the updated values
            String newCountStr = countEditText.getText().toString();
            String newDescription = descriptionEditText.getText().toString();

            if (newCountStr.isEmpty() || newDescription.isEmpty()) {
                Toast.makeText(ModifyItemActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int newCount = Integer.parseInt(newCountStr);

            // Save the new values to the database
            InventoryDBHandler dbHandler = new InventoryDBHandler(this);
            dbHandler.updateItem(itemName, newCount, newDescription);

            // Show a success message and navigate back to inventory
            Toast.makeText(ModifyItemActivity.this, "Item modified successfully", Toast.LENGTH_SHORT).show();

            // Navigate to InventoryActivity
            Intent intent = new Intent(ModifyItemActivity.this, InventoryActivity.class);
            startActivity(intent);
            finish();
        });
    }
}