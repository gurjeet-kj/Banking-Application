package com.example.bankingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtPassword, edtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);

        btnLogin.setOnClickListener(v -> {
            if (validate()) {
                Intent intent = new Intent(this, SecondPage.class);
                intent.putExtra("username",edtUsername.getText().toString());
                startActivity(intent);
            }
        });

    }


    public Boolean validate() {
        if (edtUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter UserName", Toast.LENGTH_LONG).show();
            return false;
        } else if (edtPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
            return false;
        } else if (!edtUsername.getText().toString().equals("gurjeet")) {
            Toast.makeText(this, "Incorrect Username", Toast.LENGTH_LONG).show();
            return false;
        } else if (!edtPassword.getText().toString().equals("123456")) {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}