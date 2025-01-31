package com.example.franckpizzasfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    return true;
                } else if (itemId == R.id.nav_menu) {
                    startActivity(new Intent(HomeActivity.this, MenuActivity.class));
                } else if (itemId == R.id.nav_orders) {
                    startActivity(new Intent(HomeActivity.this, OrdersActivity.class));
                } else if (itemId == R.id.nav_cart) {
                    startActivity(new Intent(HomeActivity.this, CartActivity.class));
                } else if (itemId == R.id.nav_account) {
                    startActivity(new Intent(HomeActivity.this, AccountActivity.class));
                }
                overridePendingTransition(0, 0);
                return true;
            }
        });

        Button btnMargherita = findViewById(R.id.button_margherita);
        if (btnMargherita != null) {
            btnMargherita.setOnClickListener(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "De retour mon cher ami", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_margherita) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hello");
            builder.setIcon(R.drawable.piz_log);
            builder.setMessage("Hello, cette fonctionnalite est en cours de devellopement");
            builder.setPositiveButton("OK", null);
            builder.show();
        }
    }
}
