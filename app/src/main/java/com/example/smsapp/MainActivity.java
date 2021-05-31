package com.example.smsapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton sendSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendSMSButton = findViewById(R.id.sendSMSButton);
        sendSMSButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SMSSenderActivity.class);
            startActivity(intent);
        });
    }
}