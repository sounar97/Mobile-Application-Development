package com.example.adhaar0139;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView res;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        res = findViewById(R.id.result);

        // Back button initialization

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close ResultActivity
            }
        });

        Intent intt = getIntent();
        String r_name = intt.getStringExtra("NAME");
        String r_aadhar = intt.getStringExtra("AADHAR");
        int age = intt.getIntExtra("AGE", 0);

        if (r_name != null && r_aadhar != null) {
            if (age >= 18) {
                res.setText("Name: " + r_name + "\nAadhar Number: " + r_aadhar + "\nYou are eligible to vote");
            } else {
                res.setText("Name: " + r_name + "\nAadhar Number: " + r_aadhar + "\nYou are not eligible to vote");
            }
        } else {
            res.setText("Invalid input.");
        }
    }

}
