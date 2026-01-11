package com.example.sehat;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class AddHabitActivity extends AppCompatActivity {

    public static final String EXTRA_HABIT_NAME = "com.example.sehat.EXTRA_HABIT_NAME";
    public static final String EXTRA_HABIT_DESCRIPTION = "com.example.sehat.EXTRA_HABIT_DESCRIPTION";
    public static final String EXTRA_HABIT_TIME = "com.example.sehat.EXTRA_HABIT_TIME";

    private int selectedHour = -1;
    private int selectedMinute = -1;

    private EditText etHabitName, etHabitDescription;
    private Button btnSaveHabit;
    private TextView tvSelectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        // Inisialisasi View
        etHabitName = findViewById(R.id.etHabitName);
        etHabitDescription = findViewById(R.id.etHabitDescription);
        btnSaveHabit = findViewById(R.id.btnSaveHabit);
        tvSelectedTime = findViewById(R.id.tvSelectedTime);

        // Setup listeners
        btnSaveHabit.setOnClickListener(v -> saveHabit());

        // TextView untuk memilih waktu sekarang memicu TimePicker
        tvSelectedTime.setOnClickListener(v -> showTimePickerDialog());
    }

    private void showTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int initialHour = (selectedHour != -1) ? selectedHour : c.get(Calendar.HOUR_OF_DAY);
        int initialMinute = (selectedMinute != -1) ? selectedMinute : c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    selectedHour = hourOfDay;
                    selectedMinute = minute;

                    // Langsung set waktu yang diformat ke TextView
                    String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                    tvSelectedTime.setText(formattedTime); // Tampilan lebih bersih
                    tvSelectedTime.setError(null);
                }, initialHour, initialMinute, true);

        timePickerDialog.show();
    }

    private void saveHabit() {
        String name = etHabitName.getText().toString().trim();
        String description = etHabitDescription.getText().toString().trim();

        // Validasi input
        if (!isInputValid(name)) {
            return;
        }

        // --- Pindah ke AktivitasBerhasilActivity ---
        Intent intent = new Intent(AddHabitActivity.this, AktivitasBerhasilActivity.class);
        intent.putExtra(EXTRA_HABIT_NAME, name);
        intent.putExtra(EXTRA_HABIT_DESCRIPTION, description);
        String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
        intent.putExtra(EXTRA_HABIT_TIME, time);

        startActivity(intent);

        Toast.makeText(this, "Kebiasaan berhasil ditambahkan", Toast.LENGTH_SHORT).show();

        // Tutup halaman ini agar tidak bisa balik lagi ke form input saat ditekan back
        finish();
    }

    private boolean isInputValid(String name) {
        if (TextUtils.isEmpty(name)) {
            etHabitName.setError("Nama kebiasaan tidak boleh kosong!");
            etHabitName.requestFocus();
            return false;
        }

        if (selectedHour == -1 || selectedMinute == -1) {
            // Memberi error pada TextView agar lebih terlihat oleh user
            tvSelectedTime.setError("Waktu harus dipilih!");
            Toast.makeText(this, "Silakan pilih waktu kebiasaan!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
