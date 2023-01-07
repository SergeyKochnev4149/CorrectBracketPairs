package com.example.correctbracketpairs;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    EditText entryField;
    TextView result;
    static final private BigInteger one = new BigInteger("1");
    static final private BigInteger two = new BigInteger("2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        entryField = findViewById(R.id.entryField);
        result = findViewById(R.id.result);
    }


    //getCountOfCorrectBracketPairs is a method of counting
    // the number of combinations of correct bracket expressions by count of bracket pairs
    public void getCountOfCorrectBracketPairs(View view) {
        String inputData = entryField.getText().toString();
        if (!inputData.isEmpty() && TextUtils.isDigitsOnly(inputData)) {
            try {
                BigInteger countOfBracketPairs = new BigInteger(inputData);
                BigInteger countOfBrackets = countOfBracketPairs.multiply(two);
                BigInteger factorial_countOfBrackets = getFactorialOfNumber(countOfBrackets);
                BigInteger factorial_countOfBracketPairs = getFactorialOfNumber(countOfBracketPairs);
                BigInteger countOfCorrectBracketPairs =
                        factorial_countOfBrackets.divide(factorial_countOfBracketPairs.multiply(getFactorialOfNumber(countOfBracketPairs.add(one))));

                result.setText("Result for " + inputData + " = " + countOfCorrectBracketPairs);
            } catch (StackOverflowError e) {
                result.setText("Result for " + inputData + " bigger then 214*10^37500");
            }
            entryField.setText("");
        } else {
            entryField.setText("");
            Toast.makeText(this, "Enter an integer please", Toast.LENGTH_SHORT).show();
        }
    }


    //getFactorialOfNumber is a recursion method for calculating the factorial of small and so big numbers
    public BigInteger getFactorialOfNumber(BigInteger number) {
        if (number.compareTo(one) <= 0)
            return one;

        BigInteger recursion = getFactorialOfNumber(number.subtract(one));
        return number.multiply(recursion);

    }

}