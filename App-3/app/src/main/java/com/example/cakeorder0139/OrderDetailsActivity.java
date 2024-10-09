package com.example.cakeorder0139;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        TextView orderSummary = findViewById(R.id.orderSummary);

        // Get data from the intent
        String flavors = getIntent().getStringExtra("flavors");
        int vanillaCount = getIntent().getIntExtra("vanillaCount", 0);
        int chocolateCount = getIntent().getIntExtra("chocolateCount", 0);
        int strawberryCount = getIntent().getIntExtra("strawberryCount", 0);
        double totalBill = getIntent().getDoubleExtra("totalBill", 0.0);
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");

        // Construct summary message
        StringBuilder summary = new StringBuilder("Order Summary:\n");
        if (vanillaCount > 0) summary.append("Vanilla: ").append(vanillaCount).append("\n");
        if (chocolateCount > 0) summary.append("Chocolate: ").append(chocolateCount).append("\n");
        if (strawberryCount > 0) summary.append("Strawberry: ").append(strawberryCount).append("\n");

        summary.append("Total Quantity: ").append(vanillaCount + chocolateCount + strawberryCount).append("\n");
        summary.append("Total Bill: ").append(totalBill).append("\n");
        summary.append("Order Date: ").append(date).append("\n");
        summary.append("Order Time: ").append(time);

        // Set the summary to the TextView
        orderSummary.setText(summary.toString());
    }
}
