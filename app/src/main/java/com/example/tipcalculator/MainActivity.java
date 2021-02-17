package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormatValue = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormatValue = NumberFormat.getPercentInstance();
    private double billAmount = 0.0;
    private double tipPercent = 0.25;
    private TextView txtBillAmount;
    private TextView txtTipPercent;
    private TextView txtTip;
    private TextView txtTotalBillAmount;
    private double totalSalary = 0.0;
    private double savingsPercent = 0.25;
    private TextView txtSavePercent;
    private TextView txtMoneySaved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBillAmount = findViewById(R.id.txtBillAmount);
        txtTipPercent = findViewById(R.id.txtTipPercent);
        txtTip = findViewById(R.id.txtTip);
        txtTotalBillAmount = findViewById(R.id.txtTotalBillAmount);
        txtTip.setText(currencyFormatValue.format(0));
        txtTotalBillAmount.setText(currencyFormatValue.format(0));
        txtSavePercent = findViewById(R.id.txtSavePercent);
        txtMoneySaved = findViewById(R.id.txtMoneySaved);
        txtMoneySaved.setText(currencyFormatValue.format(0));
        EditText edtMoneyAmount = findViewById(R.id.edtMoneyAmount);
        edtMoneyAmount.addTextChangedListener(tipEdtMoneyAmountTextWatcher);
        SeekBar seekBarPercent = findViewById(R.id.seekBarPercent);
        seekBarPercent.setOnSeekBarChangeListener(tipSeekBarChangeListener);

        EditText edtSalary = findViewById(R.id.edtSalary);
        edtSalary.addTextChangedListener(edtSalaryChangedTextWatcher);
        SeekBar seekBarSavePercent = findViewById(R.id.seekBarSavePercent);
        seekBarSavePercent.setOnSeekBarChangeListener(seekBarSavingsPercentChangedListener);


    }
    private TextWatcher tipEdtMoneyAmountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                billAmount = Double.parseDouble(s.toString())/100.0;
                txtBillAmount.setText(currencyFormatValue.format(billAmount));

            }catch(NumberFormatException e){
                txtBillAmount.setText("");
                billAmount = 0.0;
            }
            calculateTip();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private final SeekBar.OnSeekBarChangeListener tipSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tipPercent = progress/100.0;
            calculateTip();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private void calculateTip(){
        txtTipPercent.setText(percentFormatValue.format(tipPercent));
        double tipValue = billAmount * tipPercent;
        double totalValue = tipValue + billAmount;
        txtTip.setText(currencyFormatValue.format(tipValue));
        txtTotalBillAmount.setText(currencyFormatValue.format(totalValue));

    }

    private final TextWatcher edtSalaryChangedTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                totalSalary = Double.parseDouble(s.toString());
                calculateSavings();
            }catch(NumberFormatException e){
                totalSalary = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private final SeekBar.OnSeekBarChangeListener seekBarSavingsPercentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            savingsPercent = progress/100.0;
            calculateSavings();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void calculateSavings(){
        txtSavePercent.setText(percentFormatValue.format(savingsPercent));
        double savedMoney = (savingsPercent * totalSalary);
        txtMoneySaved.setText(currencyFormatValue.format(savedMoney));
    }

}