package com.example.franckpizzasfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView userNameTextView, userEmailTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();

        userNameTextView = findViewById(R.id.user_name);
        userEmailTextView = findViewById(R.id.user_email);
        logoutButton = findViewById(R.id.logout_button);

        loadUserInfo();

        logoutButton.setOnClickListener(v -> logout());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_account) {
                    return true;
                } else if (itemId == R.id.nav_home) {
                    startActivity(new Intent(AccountActivity.this, HomeActivity.class));
                } else if (itemId == R.id.nav_menu) {
                    startActivity(new Intent(AccountActivity.this, MenuActivity.class));
                } else if (itemId == R.id.nav_orders) {
                    startActivity(new Intent(AccountActivity.this, OrdersActivity.class));
                } else if (itemId == R.id.nav_cart) {
                    startActivity(new Intent(AccountActivity.this, CartActivity.class));
                }

                overridePendingTransition(0, 0);
                return true;
            }
        });
    }

    private void loadUserInfo() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userEmailTextView.setText(user.getEmail());
            if (user.getDisplayName() != null && !user.getDisplayName().isEmpty()) {
                userNameTextView.setText(user.getDisplayName());
            } else {
                userNameTextView.setText("Utilisateur");
            }
        } else {
            Toast.makeText(this, "Aucun utilisateur connecté", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(AccountActivity.this, LoginActivity.class));
        finish();
    }
}
