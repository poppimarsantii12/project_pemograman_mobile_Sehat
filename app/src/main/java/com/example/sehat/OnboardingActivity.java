package com.example.sehat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        CardView cardLogin = findViewById(R.id.cardLogin);
        CardView cardSignUp = findViewById(R.id.cardSignUp);

        // Aksi ketika tombol Login diklik
        cardLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Berhasil Login!", Toast.LENGTH_SHORT).show();

            // PINDAH LANGSUNG KE DASHBOARD
            Intent intent = new Intent(OnboardingActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });

        // Aksi ketika tombol Sign Up diklik (jika ingin ke Dashboard tetap gunakan yang lama)
        cardSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(OnboardingActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }
}