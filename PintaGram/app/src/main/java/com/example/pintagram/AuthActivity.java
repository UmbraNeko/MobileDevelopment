package com.example.pintagram;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.data.FirebaseAuthRepository;
import com.example.domain.AuthRepository;
import com.example.domain.LoginUser;
import com.example.domain.RegisterUser;

public class AuthActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, nameEditText;
    private Button authButton;
    private TextView switchAuthMode;
    private AuthViewModel authViewModel;
    private boolean isLoginMode = true; // Флаг для отслеживания режима

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Инициализация элементов интерфейса
        emailEditText = findViewById(R.id.email_input);
        passwordEditText = findViewById(R.id.password_input);
        nameEditText = findViewById(R.id.name_input);
        authButton = findViewById(R.id.auth_button);
        switchAuthMode = findViewById(R.id.switch_auth_mode);

        // Инициализация репозитория и UseCases
        AuthRepository authRepository = new FirebaseAuthRepository();
        LoginUser loginUser = new LoginUser(authRepository);
        RegisterUser registerUser = new RegisterUser(authRepository);

        authViewModel = new ViewModelProvider(this, new AuthViewModelFactory(getApplication(), loginUser, registerUser)).get(AuthViewModel.class);

        // Наблюдение за успешным логином
        authViewModel.getLoginSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                String email = emailEditText.getText().toString();
                saveUserData(email, nameEditText.getText().toString());
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });

        // Наблюдение за ошибками
        authViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Логика авторизации/регистрации
        authButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String name = nameEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty() || (!isLoginMode && name.isEmpty())) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isLoginMode) {
                authViewModel.login(email, password);
            } else {
                authViewModel.register(name, email, password);
            }
        });

        // Переключение между режимами
        switchAuthMode.setOnClickListener(v -> toggleAuthMode());
    }

    private void toggleAuthMode() {
        isLoginMode = !isLoginMode;
        if (isLoginMode) {
            authButton.setText("Авторизоваться");
            switchAuthMode.setText("У вас ещё нет аккаунта? Зарегистрируйтесь прямо сейчас!");
            nameEditText.setVisibility(android.view.View.GONE);
        } else {
            authButton.setText("Зарегистрироваться");
            switchAuthMode.setText("У вас уже есть аккаунт? Авторизуйтесь!");
            nameEditText.setVisibility(android.view.View.VISIBLE);
        }
    }

    private void saveUserData(String email, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        if (name != null) {
            editor.putString("name", name);
        }
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }
}
