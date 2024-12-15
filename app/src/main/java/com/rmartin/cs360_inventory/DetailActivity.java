package com.rmartin.cs360_inventory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView itemNameTextView;
    private TextView itemCountTextView;
    private TextView itemDescriptionTextView;
    private Button backToInventoryButton;
    private Button modifyItemButton; // Declare the Modify Item button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        itemNameTextView = findViewById(R.id.productName);
        itemCountTextView = findViewById(R.id.productCount);
        itemDescriptionTextView = findViewById(R.id.productDescription);
        backToInventoryButton = findViewById(R.id.backToInventoryButton);
        modifyItemButton = findViewById(R.id.modifyItemButton); // Initialize the Modify Item button

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
    }
}