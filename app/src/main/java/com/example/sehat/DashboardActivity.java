package com.example.sehat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // PERBAIKAN 1: Pastikan layoutnya adalah layout dashboard, bukan berhasil
        setContentView(R.layout.activity_dashboard);

        // PERBAIKAN 2: Pastikan ID tombol ini ada di activity_dashboard.xml
        // Biasanya tombol untuk menambah habit (ikon +)
        View btnAddHabit = findViewById(R.id.btn_tambah);

        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // URUTAN: Dari Dashboard (3) ke AddHabit (4)
                Intent intent = new Intent(DashboardActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }
        });
    }
}