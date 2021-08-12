package com.example.bankingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankingapplication.models.AccountsModel;

import java.util.ArrayList;

public class TransferAmount extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearOther;
    Spinner spinnerAccountFrom, spinnerAccountTo;
    EditText edtClientname, edtClientAccountnumber, edtAmount;
    Button btn_cancle, btn_transfer;

    TextView tvClose;

    ArrayList<String> accounts = new ArrayList<>();
    ArrayList<String> accountTo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_amount);

        linearOther = findViewById(R.id.linearOther);

        spinnerAccountFrom = findViewById(R.id.spinnerAccountFrom);
        spinnerAccountTo = findViewById(R.id.spinnerAccountTo);

        edtClientname = findViewById(R.id.edtClientname);
        edtClientAccountnumber = findViewById(R.id.edtClientAccountnumber);
        edtAmount = findViewById(R.id.edtAmount);

        btn_cancle = findViewById(R.id.btn_cancle);
        btn_transfer = findViewById(R.id.btn_transfer);

        tvClose = findViewById(R.id.tvClosee);


        tvClose.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);
        btn_transfer.setOnClickListener(this);


        for (int i = 0; i < SecondPage.arraylist.size(); i++) {
            accounts.add(SecondPage.arraylist.get(i).getAccountName());
        }

        for (int i = 0; i < SecondPage.arraylist.size(); i++) {
            accountTo.add(SecondPage.arraylist.get(i).getAccountName());
        }
        accountTo.add("other");

        ArrayAdapter<String> adapteraccounts = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, accounts);
        adapteraccounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccountFrom.setAdapter(adapteraccounts);


        ArrayAdapter<String> adapterFromaccounts = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, accountTo);
        adapterFromaccounts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccountTo.setAdapter(adapterFromaccounts);

        spinnerAccountTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) linearOther.setVisibility(View.VISIBLE);
                else linearOther.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancle:
            case R.id.tvClosee: {
                finish();
                break;
            }
            case R.id.btn_transfer: {
                if (spinnerAccountTo.getSelectedItemPosition() == spinnerAccountFrom.getSelectedItemPosition()) {
                    Toast.makeText(this, "Recipient & Sender Account Can't Be Same", Toast.LENGTH_SHORT).show();
                } else if (linearOther.getVisibility() == View.VISIBLE) {
                    if (edtClientname.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Enter Account Holder Name", Toast.LENGTH_SHORT).show();
                    } else if (edtClientAccountnumber.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Enter Account Number", Toast.LENGTH_SHORT).show();
                    } else if (edtAmount.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Enter Amount To Transfer", Toast.LENGTH_SHORT).show();
                    }
                    setBackresult(true);
                } else if (edtAmount.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Enter Amount To Transfer", Toast.LENGTH_SHORT).show();
                } else {
                    setBackresult(false);
                }

                break;
            }
        }
    }

    public void setBackresult(Boolean sendToOther) {
        int balance = Integer.parseInt(SecondPage.arraylist.get(spinnerAccountFrom.getSelectedItemPosition()).getBalance());
        if (Integer.parseInt(edtAmount.getText().toString()) > balance) {
            Toast.makeText(this, "You don't have enough balance in your selected account", Toast.LENGTH_SHORT).show();
        } else {
            AccountsModel a = SecondPage.arraylist.get(spinnerAccountFrom.getSelectedItemPosition());
            a.setBalance(String.valueOf(balance - Integer.parseInt(edtAmount.getText().toString())));
            SecondPage.arraylist.set(spinnerAccountFrom.getSelectedItemPosition(), a);

            if (!sendToOther) {
                int balanceTo = Integer.parseInt(SecondPage.arraylist.get(spinnerAccountTo.getSelectedItemPosition()).getBalance());

                AccountsModel b = SecondPage.arraylist.get(spinnerAccountTo.getSelectedItemPosition());
                b.setBalance(String.valueOf(balanceTo + Integer.parseInt(edtAmount.getText().toString())));

                SecondPage.arraylist.set(spinnerAccountTo.getSelectedItemPosition(), b);
            }


            String toastMsg="Successfully Transfer $"+ edtAmount.getText().toString()+" to Account Number: "+ edtClientAccountnumber.getText().toString()+"\n From My Account: "+ SecondPage.arraylist.get(spinnerAccountFrom.getSelectedItemPosition()).getAccountNumber();

            Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

           Intent in = new Intent();
            in.putExtra("sendToOther", sendToOther);
            in.putExtra("amount", edtAmount.getText().toString());
            in.putExtra("otherAccount", edtClientAccountnumber.getText().toString());
            in.putExtra("myaccountNumber", SecondPage.arraylist.get(spinnerAccountFrom.getSelectedItemPosition()).getAccountNumber());

            setResult(RESULT_OK, in);
            finish();
        }
    }
}