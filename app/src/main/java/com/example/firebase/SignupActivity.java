package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firebase.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog dialog;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        binding.signupButton.setOnClickListener(v -> {
            String name = Objects.requireNonNull(binding.nameEt.getText()).toString().trim();
            String email = Objects.requireNonNull(binding.emailEt.getText()).toString().trim();
            String password = Objects.requireNonNull(binding.passwordEt.getText()).toString().trim();

            if (name.isEmpty()) {
                binding.nameEt.setError("Please enter name");
            } else if (email.isEmpty()) {
                binding.emailEt.setError("Please enter email");
            } else if (password.isEmpty()) {
                binding.passwordEt.setError("Please enter password");
            } else if (password.length() < 6) {
                binding.passwordEt.setError("Please enter at least 6 digits");
            } else {
                dialog.setMessage("Signing up....");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                        String currentUserId = firebaseAuth.getCurrentUser().getUid();
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserId);
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("name", name);
                        userInfo.put("email", email);
                        userInfo.put("password", password);
                        databaseReference.updateChildren(userInfo).addOnCompleteListener(task1 -> {
                            Log.d("Firebase>>>>","Data saved successfully");
                        });
                        sendEmailVerification();
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

        binding.backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                Toast.makeText(SignupActivity.this, "Verification email sent", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            });
        } else {
            Toast.makeText(this, "Failed to send verification mail", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

}