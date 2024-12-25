package com.example.trk_5b;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trk_5b.httpclient.ApiClient;
import com.example.trk_5b.model.DataItem;
import com.example.trk_5b.model.ResponseMahasiswa;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;  // Import ActionBarDrawerToggle

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private DrawerLayout drawerLayout; // DrawerLayout untuk sidebar
    private ActionBarDrawerToggle drawerToggle; // DrawerToggle untuk menampilkan hamburger icon
    private Button btnAbout, btnJadwal, btnLogout; // Buttons inside drawer layout

    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Konfigurasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup DrawerLayout untuk navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        // Initialize the ActionBarDrawerToggle to lide from the right
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer
        );

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        // Initialize buttons inside the navigation drawer
        btnAbout = findViewById(R.id.btnAbout);
        btnJadwal = findViewById(R.id.btnJadwal);
        btnLogout = findViewById(R.id.btnLogout);
        textView = findViewById(R.id.user_details);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }

        // Set listeners for the buttons in the drawer
        btnAbout.setOnClickListener(v -> navigateToAbout());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        fetchMahasiswa(); // Panggil data dari API
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Ketika item menu toolbar dipilih
        if (item.getItemId() == R.id.about_page) {
            showIcon();  // Open the sidebar instead of going directly to About
        } else if (drawerToggle.onOptionsItemSelected(item)) {
            // Handle the drawer toggle icon click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showIcon() {
        drawerLayout.openDrawer(GravityCompat.END); // Open the drawer from the right
    }

    private void fetchMahasiswa() {
        Call<ResponseMahasiswa> call = ApiClient.getApiInterface().getMahasiswa();

        call.enqueue(new Callback<ResponseMahasiswa>() {
            @Override
            public void onResponse(Call<ResponseMahasiswa> call, Response<ResponseMahasiswa> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DataItem> mahasiswaList = response.body().getData();
                    mahasiswaAdapter = new MahasiswaAdapter(MainActivity.this, mahasiswaList);
                    recyclerView.setAdapter(mahasiswaAdapter);

                    // Tambahkan listener untuk klik item
                    mahasiswaAdapter.setOnItemClickListener((view, position) -> {
                        DataItem mhs = mahasiswaList.get(position);

                        // Kirim data ke DetailActivity
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("mhs_data", mhs); // Passing objek DataItem
                        startActivity(intent);
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch mahasiswa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMahasiswa> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Navigate to About Activity
    private void navigateToAbout() {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
        drawerLayout.closeDrawer(GravityCompat.END); // Close the drawer after navigating
    }

}