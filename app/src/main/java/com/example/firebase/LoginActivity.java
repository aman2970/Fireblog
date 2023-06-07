package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebase.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog dialog;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        binding.loginButton.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.emailEt.getText()).toString().trim();
            String password = Objects.requireNonNull(binding.passwordEt.getText()).toString().trim();
            if (email.isEmpty()) {
                binding.emailEt.setError("Please enter email");
            } else if (password.isEmpty()) {
                binding.passwordEt.setError("Please enter password");
            } else {
                dialog.setMessage("Login in progrss...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Login unsuccessful try again", Toast.LENGTH_SHORT).show();
                    }

                });
            }

        });

        binding.signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}