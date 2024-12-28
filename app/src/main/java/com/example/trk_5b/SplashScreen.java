package com.example.trk_5b;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000; // 3 detik
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBar);

        // Simulasi loading
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(SPLASH_TIME_OUT / 80); // Animasi loading
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Pindah ke MainActivity setelah selesai loading
            runOnUiThread(() -> {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        }).start();
    }
}
