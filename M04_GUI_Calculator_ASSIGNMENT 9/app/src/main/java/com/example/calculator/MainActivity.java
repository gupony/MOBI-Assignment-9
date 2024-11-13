package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    // input and result fields
    private EditText textN1, textN2, textANS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textN1 = findViewById(R.id.editTextN1);
        textN2 = findViewById(R.id.editTextN2);
        textANS = findViewById(R.id.editTextNumAns);

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        findViewById(R.id.b_Add).setOnClickListener(view -> calculate(Operation.ADD));
        findViewById(R.id.b_Subtract).setOnClickListener(view -> calculate(Operation.SUBTRACT));
        findViewById(R.id.b_Multiply).setOnClickListener(view -> calculate(Operation.MULTIPLY));
        findViewById(R.id.b_Divide).setOnClickListener(view -> calculate(Operation.DIVIDE));
    }

    private void calculate(Operation operation) {
        double result;
        try {
            double num1 = Double.parseDouble(textN1.getText().toString());
            double num2 = Double.parseDouble(textN2.getText().toString());

            //  operations
            switch (operation) {
                case ADD:
                    result = num1 + num2;
                    Log.d("Calculator", "Add: " + num1 + " + " + num2);
                    break;
                case SUBTRACT:
                    result = num1 - num2;
                    Log.d("Calculator", "Subtract: " + num1 + " - " + num2);
                    break;
                case MULTIPLY:
                    result = num1 * num2;
                    break;
                case DIVIDE:
                    if (num2 == 0) {
                        Log.w("Calculator", "Attempt to divide by zero");
                        textANS.setText(getString(R.string.error_divide_by_zero));
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    return;
            }
            textANS.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textANS.setText(getString(R.string.error_invalid_input));
            Log.w("Calculator", "Invalid input: " + e.getMessage());
        }
    }

    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
