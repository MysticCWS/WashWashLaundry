package com.example.washwashlaundry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button confirmPaymentButton = findViewById(R.id.confirmPaymentButton);

        confirmPaymentButton.setOnClickListener(v -> {
            Toast.makeText(PaymentActivity.this, "Payment Confirmed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PaymentActivity.this, OrderSummaryActivity.class));
            finish();
        });
    }
}