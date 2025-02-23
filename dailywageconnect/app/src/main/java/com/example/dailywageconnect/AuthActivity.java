package com.example.dailywageconnect;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailywageconnect.database.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AuthActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton, registerButton;
    private FirebaseAuth mAuth;
    private DatabaseHelper dbHelper;  // SQLite Database Helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.registerBtn);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new DatabaseHelper(this);  // Initialize SQLite Helper

        loginButton.setOnClickListener(view -> loginUser());
        registerButton.setOnClickListener(view -> registerUser());
    }

    // 🔹 Register User and Ask for Role
    private void registerUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(AuthActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        saveUserRole(email);  // Ask for Role and Save
                    } else {
                        Toast.makeText(AuthActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 🔹 Login User and Redirect Based on Role
    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        checkUserRole(email);  // Check Role and Redirect
                    } else {
                        Toast.makeText(AuthActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 🔹 Ask User to Select Role After Registration
    private void saveUserRole(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Your Role");
        String[] roles = {"Worker", "Host"};

        builder.setItems(roles, (dialog, which) -> {
            String selectedRole = roles[which].toLowerCase();
            dbHelper.insertUser(email, selectedRole);  // Save Role in SQLite

            if (selectedRole.equals("worker")) {
                startActivity(new Intent(AuthActivity.this, WorkerDashboard.class));
            } else {
                startActivity(new Intent(AuthActivity.this, HostDashboard.class));
            }
            finish();
        });

        builder.show();
    }

    // 🔹 Check User Role After Login and Redirect
    private void checkUserRole(String email) {
        String role = dbHelper.getUserRole(email);
        if (role != null) {
            if (role.equals("worker")) {
                startActivity(new Intent(AuthActivity.this, WorkerDashboard.class));
            } else {
                startActivity(new Intent(AuthActivity.this, HostDashboard.class));
            }
            finish();
        } else {
            saveUserRole(email);  // If role is missing, ask the user
        }
    }
}
