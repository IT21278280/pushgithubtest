package com.example.old_agecal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    EditText BirthYear; // this is to get the birth year from the user to java program
    TextView DisAns; // this object is to display the age to the user in years
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BirthYear = BirthYear.findViewById(); // from here onwards I am going to refer user input to this object
        DisAns = BirthYear.findViewById(); // from here onwards I am going to refer label ans to this object


        Button BtnCalc = BtnCalc.findViewById();
        BtnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalcAge(v);
            }
        });

    }

    private void setContentView(int activity_main) {
    }

    public void CalcAge(View view){

        Integer birthYear = Integer.valueOf(BirthYear.getText().toString());
        Integer currYear = Calendar.getInstance().get(Calendar.YEAR);
        String ans = String.valueOf(currYear - birthYear);

        DisAns.setText("Your age is " + ans + " years old.");
        //fffffffffff comment testing
        //jjjjjjjjjjjj comment testing
        //








    }
}