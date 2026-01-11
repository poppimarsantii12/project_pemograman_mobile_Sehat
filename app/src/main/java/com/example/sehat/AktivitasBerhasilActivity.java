package com.example.sehat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class AktivitasBerhasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_aktivitas_berhasil);

        // Inisialisasi Views sesuai dengan ID di layout_aktivitas_berhasil.xml
        TextView tvDynamicMessage = findViewById(R.id.tv_dynamic_message); // Menggunakan ID yang baru ditambahkan
        AppCompatButton btnOk = findViewById(R.id.btn_ok); // ID ini sudah benar

        // Ambil data dari Intent
        Intent intent = getIntent();

        // Gunakan konstanta dari AddHabitActivity untuk keamanan
        String namaAktivitas = intent.getStringExtra(AddHabitActivity.EXTRA_HABIT_NAME);

        // Set data ke TextView yang benar
        // Pastikan view dan data tidak null
        if (tvDynamicMessage != null && !TextUtils.isEmpty(namaAktivitas)) {
            // Buat pesan dinamis menggunakan nama aktivitas yang diterima
            String message = "Aktivitas '" + namaAktivitas + "' telah ditambahkan ke catatan harianmu.";
            tvDynamicMessage.setText(message);
        }
        // Jika namaAktivitas null/kosong, TextView akan menampilkan teks default dari XML.

        // Pastikan tombol OK tidak null sebelum menambahkan listener untuk mencegah crash
        if (btnOk != null) {
            btnOk.setOnClickListener(v -> {
                // Lanjut ke Progress Tracker Activity
                Intent progressIntent = new Intent(AktivitasBerhasilActivity.this, ProgressTrackerActivity.class);

                // Membersihkan stack agar tidak kembali ke halaman 'add' atau 'success'
                // FLAG_ACTIVITY_CLEAR_TASK akan memulai activity baru dalam task baru, membersihkan yang lama.
                progressIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(progressIntent);
                finish(); // Menutup activity ini secara eksplisit
            });
        }
    }
}
