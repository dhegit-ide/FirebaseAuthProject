package com.dhegit.firebaseauthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView tvNama, tvTanggal;
    private FirebaseUser fUser;
    private Button btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNama = findViewById(R.id.tv_nama);
        tvTanggal = findViewById(R.id.tv_tanggal);
        btnKeluar = findViewById(R.id.btn_keluar);
        fUser = FirebaseAuth.getInstance().getCurrentUser();

        if (fUser != null) {
            tvNama.setText(fUser.getDisplayName());
        } else {
            tvNama.setError("User tidak ditemukan");
        }

        Calendar kalender = Calendar.getInstance();
        String tanggalSekarang = DateFormat.getDateInstance(android.icu.text.DateFormat.FULL).format(kalender.getTime());
        tvTanggal.setText(tanggalSekarang);

        btnKeluar.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }
}