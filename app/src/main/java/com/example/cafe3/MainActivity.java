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

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите email и пароль", Toast.LENGTH_SHORT).show();
            return;
        }

        // Авторизация пользователя
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Авторизация успешна, переход к следующему экрану
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                        // Переход к главному экрану
                        Intent intent2 = new Intent(MainActivity.this, MainActivityMenu.class);
                        startActivity(intent2);
                        finish();
                    } else {
                        // Если авторизация не удалась, показываем сообщение
                        Toast.makeText(MainActivity.this, "Ошибка авторизации: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void onRegisterButtonClick(View view) {
        // Переход к MainActivityMenu
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}
