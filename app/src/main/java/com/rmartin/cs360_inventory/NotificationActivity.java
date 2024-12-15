package com.rmartin.cs360_inventory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.activity.EdgeToEdge;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Switch;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NotificationActivity extends AppCompatActivity {

    private TextView notificationText;
    private Button requestPermissionButton;
    private EditText phoneNumberInput;
    private Switch smsPermissionToggle;
    private Button backToInventoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationText = findViewById(R.id.notification_text);
        requestPermissionButton = findViewById(R.id.request_permission_button);
        phoneNumberInput = findViewById(R.id.phone_number_input);
        smsPermissionToggle = findViewById(R.id.sms_permission_toggle);
        backToInventoryButton = findViewById(R.id.backToInventoryButton);

        checkAndHandleSMSPermission();

        smsPermissionToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestSMSPermission();
            } else {
                notificationText.setText("SMS Notifications Disabled.");
                requestPermissionButton.setVisibility(View.GONE);
            }
        });

        requestPermissionButton.setOnClickListener(v -> requestSMSPermission());

        backToInventoryButton.setOnClickListener(v -> {
            // Navigate back to InventoryActivity
            Intent intent = new Intent(NotificationActivity.this, InventoryActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void checkAndHandleSMSPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            notificationText.setText("SMS Notifications Enabled");
            requestPermissionButton.setVisibility(View.GONE);
            // Trigger inventory notification if low inventory is detected
            sendInventoryNotification();
        } else {
            notificationText.setText("SMS Notifications Disabled. Please grant permission.");
            requestPermissionButton.setVisibility(View.VISIBLE);
        }
    }

    private void requestSMSPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
    }

    private void sendInventoryNotification() {
        String lowInventoryMessage = "Alert: Inventory Count is at Zero for this item!";
        notificationText.append("\nNotification Sent: " + lowInventoryMessage);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkAndHandleSMSPermission();
            } else {
                notificationText.setText("Permission Denied. SMS Notifications Disabled.");
            }
        }
    }
}