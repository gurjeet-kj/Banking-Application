package com.example.bankingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankingapplication.models.AccountsModel;

import java.util.ArrayList;

public class CustomDialogClass extends AppCompatActivity implements View.OnClickListener{
      //  android.view.View.OnClickListener {

    public Button btn_cancle, btn_pay;

    ArrayList<String> accounts = new ArrayList<>();

    EditText edtSubscription, edtAmount;

    Spinner spinnerAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_pay_bill);

        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        btn_pay = (Button) findViewById(R.id.btn_pay);

        edtSubscription = (EditText) findViewById(R.id.edtSubscription);
        edtAmount = (EditText) findViewById(R.id.edtAmount);

        btn_cancle.setOnClickListener(this);
        btn_pay.setOnClickListener(this);


        String[] arraySpinner = new String[]{
                "Hydro", "Water", "Gas", "Phone"
        };
        Spinner spinnerUtility = (Spinner) findViewById(R.id.spinnerUtility);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUtility.setAdapter(adapter);


        for (int i = 0; i < SecondPage.arraylist.size(); i++) {
            accounts.add(SecondPage.arraylist.get(i).getAccountName());
        }


        spinnerAccount = (Spinner) findViewById(R.id.spinnerAccount);
        ArrayAdapter<String> adapteraccounts = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, accounts);
        adapteraccounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccount.setAdapter(adapteraccounts);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancle:
                finish();
                break;

            case R.id.btn_pay:

                int balance = Integer.parseInt(SecondPage.arraylist.get(spinnerAccount.getSelectedItemPosition()).getBalance());

                if (edtSubscription.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter Subscription Number Of Utility", Toast.LENGTH_SHORT).show();
                } else if (edtAmount.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter Amount To Be Pay", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(edtAmount.getText().toString()) > balance) {
                    Toast.makeText(this, "You Don't Have Enough Balance In Your Selected Account", Toast.LENGTH_SHORT).show();
                } else {
                    AccountsModel a = SecondPage.arraylist.get(spinnerAccount.getSelectedItemPosition());
                    a.setBalance(String.valueOf(balance - Integer.parseInt(edtAmount.getText().toString())));

                    SecondPage.arraylist.set(spinnerAccount.getSelectedItemPosition(), a);

                    Toast.makeText(this, "Successfully paid amount: "+edtAmount.getText().toString()+" , Subscription number: "+edtSubscription.getText().toString(), Toast.LENGTH_LONG).show();


                   Intent in = new Intent();
                    in.putExtra("amount", edtAmount.getText().toString());
                    in.putExtra("subscription", edtSubscription.getText().toString());


                    setResult(RESULT_OK, in);
                    finish();

                }
                break;
            default:
                break;
        }
    }
}
