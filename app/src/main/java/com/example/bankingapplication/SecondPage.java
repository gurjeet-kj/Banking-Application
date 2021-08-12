package com.example.bankingapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapplication.adapters.AccountsAdapter;
import com.example.bankingapplication.adapters.HistoryAdapter;
import com.example.bankingapplication.models.AccountsModel;
import com.example.bankingapplication.models.History;

import java.util.ArrayList;

public class SecondPage extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<AccountsModel> arraylist = new ArrayList<>();
    ArrayList<History> historyList = new ArrayList<>();

    RecyclerView rvAccounts, rvHistory;

    LinearLayout linearHistory;

    AccountsAdapter accountsAdapter;
    HistoryAdapter historyAdapter;

    Button btnPay, btnTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        TextView tvUsername = findViewById(R.id.tvUsername);
        //tvUsername.setText("Welcome, Gurjeet KJ");
        tvUsername.setText("Welcome "+getIntent().getStringExtra("username"));
        rvAccounts = findViewById(R.id.rvPayees);
        rvHistory = findViewById(R.id.rvHistory);

        btnPay = findViewById(R.id.btnPay);
        btnTransfer = findViewById(R.id.btnTransfer);

        linearHistory = findViewById(R.id.linearHistory);

        arraylist.add(new AccountsModel(1, "Scotia Bank", "100****786", "1000"));
        arraylist.add(new AccountsModel(2, "EQ Bank", "235****123", "1500"));
        arraylist.add(new AccountsModel(3, "Royal Bank", "125****101", "2000"));

        accountsAdapter = new AccountsAdapter(arraylist);
        rvAccounts.setAdapter(accountsAdapter);


        historyAdapter = new HistoryAdapter(historyList);
        rvHistory.setAdapter(historyAdapter);


        btnTransfer.setOnClickListener(this);
        btnPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPay: {
                startActivityForResult(new Intent(this, CustomDialogClass.class), 100);
                break;
            }
            case R.id.btnTransfer: {
                startActivityForResult(new Intent(this, TransferAmount.class), 200);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                accountsAdapter.notifyDataSetChanged();
                if (data.getBooleanExtra("sendToOther", false) == true) {
                    String toastMsg= "Your Account Number: " + data.getStringExtra("myaccountNumber")+ " has been debited with amount $" + data.getStringExtra("amount")+"\nSent money to Account number " + data.getStringExtra("otherAccount");


                    Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

                }


            } else if (requestCode == 100) {
                accountsAdapter.notifyDataSetChanged();

                linearHistory.setVisibility(View.VISIBLE);

                historyList.add(new History(data.getStringExtra("subscription"), data.getStringExtra("amount")));
                historyAdapter.notifyDataSetChanged();
            }
        }

    }
}