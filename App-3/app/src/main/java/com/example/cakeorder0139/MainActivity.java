package com.example.cakeorder0139;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private CheckBox vanilla, chocolate, strawberry;
    private Button vanillaIncrease, vanillaDecrease, chocolateIncrease, chocolateDecrease, strawberryIncrease, strawberryDecrease;
    private TextView vanillaQuantity, chocolateQuantity, strawberryQuantity;
    private Button dateButton, timeButton, orderButton;
    private String selectedDate, selectedTime;

    private int vanillaCount = 0;
    private int chocolateCount = 0;
    private int strawberryCount = 0;

    private final double VANILLA_PRICE = 100.0;
    private final double CHOCOLATE_PRICE = 150.0;
    private final double STRAWBERRY_PRICE = 200.0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vanilla = findViewById(R.id.Vanilla);
        chocolate = findViewById(R.id.Chocolate);
        strawberry = findViewById(R.id.Strawberry);
        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        orderButton = findViewById(R.id.orderButton);

        vanillaQuantity = findViewById(R.id.vanillaQuantity);
        vanillaIncrease = findViewById(R.id.vanillaIncrease);
        vanillaDecrease = findViewById(R.id.vanillaDecrease);

        chocolateQuantity = findViewById(R.id.chocolateQuantity);
        chocolateIncrease = findViewById(R.id.chocolateIncrease);
        chocolateDecrease = findViewById(R.id.chocolateDecrease);

        strawberryQuantity = findViewById(R.id.strawberryQuantity);
        strawberryIncrease = findViewById(R.id.strawberryIncrease);
        strawberryDecrease = findViewById(R.id.strawberryDecrease);

        vanillaIncrease.setOnClickListener(v -> {
            vanillaCount++;
            vanillaQuantity.setText(String.valueOf(vanillaCount));
        });

        vanillaDecrease.setOnClickListener(v -> {
            if (vanillaCount > 0) {
                vanillaCount--;
                vanillaQuantity.setText(String.valueOf(vanillaCount));
            }
        });

        chocolateIncrease.setOnClickListener(v -> {
            chocolateCount++;
            chocolateQuantity.setText(String.valueOf(chocolateCount));
        });

        chocolateDecrease.setOnClickListener(v -> {
            if (chocolateCount > 0) {
                chocolateCount--;
                chocolateQuantity.setText(String.valueOf(chocolateCount));
            }
        });

        strawberryIncrease.setOnClickListener(v -> {
            strawberryCount++;
            strawberryQuantity.setText(String.valueOf(strawberryCount));
        });

        strawberryDecrease.setOnClickListener(v -> {
            if (strawberryCount > 0) {
                strawberryCount--;
                strawberryQuantity.setText(String.valueOf(strawberryCount));
            }
        });

        dateButton.setOnClickListener(v -> showDatePicker());
        timeButton.setOnClickListener(v -> showTimePicker());
        orderButton.setOnClickListener(v -> placeOrder());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year; // Format: DD/MM/YYYY
            dateButton.setText(selectedDate);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute); // Format: HH:MM
            timeButton.setText(selectedTime);
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    private void placeOrder() {
        StringBuilder selectedFlavors = new StringBuilder();
        if (vanillaCount > 0) selectedFlavors.append("Vanilla: ").append(vanillaCount).append(" ");
        if (chocolateCount > 0) selectedFlavors.append("Chocolate: ").append(chocolateCount).append(" ");
        if (strawberryCount > 0) selectedFlavors.append("Strawberry: ").append(strawberryCount).append(" ");

        if (selectedFlavors.length() == 0 || selectedDate == null || selectedTime == null) {
            Toast.makeText(this, "Please select at least one cake, date, and time", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalBill = calculateBill();

        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("flavors", selectedFlavors.toString().trim());
        intent.putExtra("vanillaCount", vanillaCount);
        intent.putExtra("chocolateCount", chocolateCount);
        intent.putExtra("strawberryCount", strawberryCount);
        intent.putExtra("totalBill", totalBill);
        intent.putExtra("date", selectedDate);
        intent.putExtra("time", selectedTime);
        startActivity(intent);
    }


    private double calculateBill() {
        return (vanillaCount * VANILLA_PRICE) + (chocolateCount * CHOCOLATE_PRICE) + (strawberryCount * STRAWBERRY_PRICE);
    }
}
