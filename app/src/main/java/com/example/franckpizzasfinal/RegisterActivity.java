package com.example.franckpizzasfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button btnRegister;
    private TextView tvAlreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.mdp);
        btnRegister = findViewById(R.id.btn_register);
        tvAlreadyHaveAccount = findViewById(R.id.btn_go_to_login);

        btnRegister.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty()) {
                emailEditText.setError("Veuillez entrer un email valide.");
                return;
            }
            if (password.isEmpty()) {
                passwordEditText.setError("Le mot de passe est requis.");
                return;
            }
            if (password.length() < 6) {
                passwordEditText.setError("Le mot de passe doit contenir au moins 6 caractères.");
                return;
            }

            registerUser(email, password);
        });

        tvAlreadyHaveAccount.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification()
                                    .addOnSuccessListener(aVoid ->
                                            Toast.makeText(RegisterActivity.this, "Un email de vérification a été envoyé.", Toast.LENGTH_LONG).show())
                                    .addOnFailureListener(e ->
                                            Toast.makeText(RegisterActivity.this, "Erreur d'envoi de l'email : " + e.getMessage(), Toast.LENGTH_SHORT).show());

                            Toast.makeText(RegisterActivity.this, "Inscription réussie, vérifiez votre email.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            passwordEditText.setError("Mot de passe trop faible. Essayez un mot de passe plus fort.");
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            emailEditText.setError("Adresse email invalide.");
                        } catch (FirebaseAuthUserCollisionException e) {
                            emailEditText.setError("Cet email est déjà utilisé. Essayez de vous connecter.");
                        } catch (Exception e) {
                            Toast.makeText(RegisterActivity.this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
