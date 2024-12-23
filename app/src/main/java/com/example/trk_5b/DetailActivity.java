package com.example.trk_5b;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.trk_5b.model.DataItem;

public class DetailActivity extends AppCompatActivity {

    private TextView namaTextView, nimTextView, kelasTextView, emailTextView, hpTextView, genderTextView, birthTextView, statusTextView, semesterTextView;
    private ImageView mahasiswaImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Bind view dengan ID dari layout
        namaTextView = findViewById(R.id.namaTextView);
        nimTextView = findViewById(R.id.nimTextView);
        kelasTextView = findViewById(R.id.kelasTextView);
        emailTextView = findViewById(R.id.emailTextView);
        hpTextView = findViewById(R.id.hpTextView);
        genderTextView = findViewById(R.id.genderTextView);
        birthTextView = findViewById(R.id.birthTextView);
        statusTextView = findViewById(R.id.statusTextView);
        semesterTextView = findViewById(R.id.semesterTextView);

        mahasiswaImageView = findViewById(R.id.mahasiswaImageView);

        // Terima data dari Intent
        DataItem mhs = getIntent().getParcelableExtra("mhs_data");

        if (mhs != null) {
            // Tampilkan data di UI
            namaTextView.setText(mhs.getNama());
            nimTextView.setText(mhs.getNim());
            kelasTextView.setText(mhs.getKelas());
            emailTextView.setText(mhs.getEmail());
            hpTextView.setText(mhs.getNoHp());
            genderTextView.setText(mhs.getGender());
            birthTextView.setText(mhs.getTempatTanggalLahir());
            statusTextView.setText(mhs.getStatus());
            semesterTextView.setText(mhs.getSemester());


            // Load gambar menggunakan Glide
            Glide.with(this)
                    .load(mhs.getGambar())
                    .into(mahasiswaImageView);
        }
    }
}
