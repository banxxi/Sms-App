package com.example.smsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSSenderActivity extends AppCompatActivity {

    private final int SEND_SMS_PERMISSION_CODE = 10;
    private EditText phoneNumber;
    private EditText message;
    private Button sendButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_sender);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_CODE);
        }

        phoneNumber = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.message);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String to = phoneNumber.getText().toString().trim();
            String content = message.getText().toString().trim();
            if(to.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Phone Number and Message is needed!", Toast.LENGTH_LONG).show();
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(to, null, content, null, null);
                Toast.makeText(this, "SMS Sent!", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == SEND_SMS_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            onBackPressed();
            Toast.makeText(this, "To send messages, the app needs the requested permission.", Toast.LENGTH_LONG).show();
        }
    }
}
