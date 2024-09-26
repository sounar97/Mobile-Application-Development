package com.example.adhaar0139;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText name, num, date;
    Button btn;
    int diff;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameBox);
        num = findViewById(R.id.aadharBox);
        date = findViewById(R.id.dateBox);
        btn = findViewById(R.id.checkBtn); // Initialize button

        // Set click listener for the button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEligibility(v);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int pYear = c.get(Calendar.YEAR);
                int pMonth = c.get(Calendar.MONTH);
                int pDate = c.get(Calendar.DATE);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Material_Light_Dialog,
                        (view, year, month, dayOfMonth) -> {

                            date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);


                            Calendar currentDate = Calendar.getInstance();
                            if (year > currentDate.get(Calendar.YEAR) ||
                                    (year == currentDate.get(Calendar.YEAR) && month + 1 > currentDate.get(Calendar.MONTH) + 1) ||
                                    (year == currentDate.get(Calendar.YEAR) && month + 1 == currentDate.get(Calendar.MONTH) + 1 && dayOfMonth > currentDate.get(Calendar.DAY_OF_MONTH))) {
                                Toast.makeText(MainActivity.this, "Please enter a correct date.", Toast.LENGTH_SHORT).show();
                            } else {

                                diff = currentDate.get(Calendar.YEAR) - year;
                            }
                        }, pYear, pMonth, pDate);
                dialog.show();
            }
        });
    }

    public void checkEligibility(View v) {
        String s_name = name.getText().toString().trim();
        String s_num = num.getText().toString().trim();
        String s_date = date.getText().toString().trim();

        if (s_name.isEmpty() || s_num.isEmpty() || s_date.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent in = new Intent(getApplicationContext(), ResultActivity.class);
        in.putExtra("NAME", s_name);
        in.putExtra("AADHAR", s_num);
        in.putExtra("AGE", diff);
        startActivity(in);
    }

}
