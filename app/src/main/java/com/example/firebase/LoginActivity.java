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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog dialog;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        dialog = new ProgressDialog(this);

      /*  authStateListener = firebaseAuth -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finishAffinity();
            }

        };*/

        if (firebaseUser != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finishAffinity();
        }


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
                        checkEmailVerification();
                    } else {
                        Toast.makeText(this, "Login unsuccessful try again", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                });
            }
        });

        binding.signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            if (firebaseUser.isEmailVerified()) {
                Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finishAffinity();
            } else {
                Toast.makeText(this, "Verify your email first", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        firebaseAuth.addAuthStateListener(authStateListener);
    }
}