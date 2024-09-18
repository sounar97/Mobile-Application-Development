package com.example.registrationform0139;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText registrationNo, name, fathersName, puMarks;
    private RadioGroup genderGroup;
    private AutoCompleteTextView placeOfBirth;
    private Spinner courseSpinner;
    private TextView registrationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrationNo = findViewById(R.id.registrationNo);
        name = findViewById(R.id.name);
        fathersName = findViewById(R.id.fathersName);
        puMarks = findViewById(R.id.puMarks);
        genderGroup = findViewById(R.id.genderGroup);
        placeOfBirth = findViewById(R.id.placeOfBirth);
        registrationDetails = findViewById(R.id.registrationDetails);
        courseSpinner = findViewById(R.id.courseSpinner);
        Button registerButton = findViewById(R.id.registerButton);

        // Set up AutoCompleteTextView for Place of Birth
        String[] cities = new String[] {"Delhi", "Mumbai", "Bangalore", "Kolkata", "Chennai", "Hyderabad", "Pune", "Jaipur"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cities);
        placeOfBirth.setAdapter(adapter);

        // Set up Spinner for Courses
        String[] courses = new String[] {"Computer Science", "Electronics", "Mechanical", "Civil", "Chemical", "Information Technology"};
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regNo = registrationNo.getText().toString();
                String userName = name.getText().toString();
                String fatherName = fathersName.getText().toString();
                String totalMarks = puMarks.getText().toString();
                int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender != null ? selectedGender.getText().toString() : "Not Selected";
                String place = placeOfBirth.getText().toString();
                String course = courseSpinner.getSelectedItem().toString();

                String details = "Registration No: " + regNo + "\n" +
                        "Name: " + userName + "\n" +
                        "Father's Name: " + fatherName + "\n" +
                        "PU Total Marks: " + totalMarks + "\n" +
                        "Gender: " + gender + "\n" +
                        "Place of Birth: " + place + "\n" +
                        "Selected Course: " + course;

                registrationDetails.setText(details);
            }
        });
    }
}
