package edu.miracosta.cs134.costalattacars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import edu.miracosta.cs134.costalattacars.model.CarLoan;

public class MainActivity extends AppCompatActivity {

    // Instance variables
    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioGroup loanTermRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wire up
        carPriceEditText = findViewById(R.id.carPriceEditText);
        downPaymentEditText = findViewById(R.id.downPaymentEditText);
        loanTermRadioGroup = findViewById(R.id.loanTermRadioGroup);
    }

    public void generateReport(View v) {
        double carPrice = Double.parseDouble(carPriceEditText.getText().toString());
        double downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
        int loanTerm;

        switch (loanTermRadioGroup.getCheckedRadioButtonId()) {
            case R.id.threeYearsRadioButton:
                loanTerm = 3;
                break;
            case R.id.fourYearsRadioButton:
                loanTerm = 4;
                break;
            default:
                loanTerm = 5;
                break;
        }

        // Instantiate intent
        Intent intent = new Intent(this, LoanSummaryActivity.class);

        // Add data to intent
        intent.putExtra("CarPrice", carPrice);
        intent.putExtra("DownPayment", downPayment);
        intent.putExtra("LoanTerm", loanTerm);

        // Fire intent
        startActivity(intent);
    }
}
