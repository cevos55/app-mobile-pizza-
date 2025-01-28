package com.example.franckpizzasfinal;

import android.util.Log; // Ajout de l'import

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Log.d("DEBUG", "onCreate called"); // Maintenant, ça devrait fonctionner
    }
}