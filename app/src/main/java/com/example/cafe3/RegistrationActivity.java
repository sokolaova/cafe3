package com.example.cafe3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cafe3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button registrationButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        registrationButton = findViewById(R.id.registration_button);
        mAuth = FirebaseAuth.getInstance();

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        Button authButton = findViewById(R.id.authorization_button);
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к activity_main
                Intent intent = new Intent(RegistrationActivity.this, com.example.cafe3.MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Регистрация успешна
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(RegistrationActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                        // Переход к следующему экрану
                        startActivity(new Intent(RegistrationActivity.this, MainActivityMenu.class));
                        finish();
                    } else {
                        // Если регистрация не удалась
                        Toast.makeText(RegistrationActivity.this, "Ошибка регистрации: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
