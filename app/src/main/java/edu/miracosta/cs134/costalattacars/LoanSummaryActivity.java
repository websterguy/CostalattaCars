package edu.miracosta.cs134.costalattacars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import edu.miracosta.cs134.costalattacars.model.CarLoan;

public class LoanSummaryActivity extends AppCompatActivity {
    // Instance variables
    private CarLoan carLoan;
    private NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    private NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());
    private TextView monthlyPaymentTextView;
    private TextView carPriceTextView;
    private TextView salesTaxRateTextView;
    private TextView taxAmountTextView;
    private TextView downPaymentTextView;
    private TextView totalCostTextView;
    private TextView borrowedAmountTextView;
    private TextView interestAmountTextView;
    private TextView loanTermTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        // Extract data from intent
        Intent intent = getIntent();
        double carPrice, downPayment;
        int loanTerm;

        carPrice = intent.getDoubleExtra("CarPrice", 0.0);
        downPayment = intent.getDoubleExtra("DownPayment", 0.0);
        loanTerm = intent.getIntExtra("LoanTerm", 5);

        // Create car loan
        carLoan = new CarLoan();
        carLoan.setPrice(carPrice);
        carLoan.setDownPayment(downPayment);
        carLoan.setLoanTerm(loanTerm);

        // Populate text views with data
        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        carPriceTextView = findViewById(R.id.carPriceTextView);
        salesTaxRateTextView = findViewById(R.id.salesTaxRateTextView);
        taxAmountTextView = findViewById(R.id.taxAmountTextView);
        downPaymentTextView = findViewById(R.id.downPaymentTextView);
        totalCostTextView = findViewById(R.id.totalCostTextView);
        borrowedAmountTextView = findViewById(R.id.borrowedAmountTextView);
        interestAmountTextView = findViewById(R.id.interestAmountTextView);
        loanTermTextView = findViewById(R.id.loanTermTextView);

        monthlyPaymentTextView.setText(currency.format(carLoan.monthlyPayment()));
        carPriceTextView.setText(currency.format(carPrice));
        percent.setMinimumFractionDigits(2);
        salesTaxRateTextView.setText(percent.format(carLoan.OCEANSIDE_TAX_RATE));
        taxAmountTextView.setText(currency.format(carLoan.taxAmount()));
        downPaymentTextView.setText(currency.format(downPayment));
        totalCostTextView.setText(currency.format(carLoan.totalCost()));
        borrowedAmountTextView.setText(currency.format(carLoan.borrowedAmount()));
        interestAmountTextView.setText(currency.format(carLoan.interestAmount()));
        loanTermTextView.setText(String.valueOf(loanTerm) + " years");
    }

    public void returnToEntry(View v) {
        // Done with LoanSummaryActivity, so finish it
        finish();
    }
}
