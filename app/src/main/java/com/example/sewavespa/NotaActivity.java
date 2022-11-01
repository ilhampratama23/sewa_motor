package com.example.sewavespa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotaActivity extends AppCompatActivity {

    TextView textTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        Intent intent = getIntent();

        textTotal = findViewById(R.id.textTotalNota);

        textTotal.setText("Rp"+(intent.getStringExtra("total")));
    }
}