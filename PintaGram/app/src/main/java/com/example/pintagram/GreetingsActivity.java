package com.example.pintagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GreetingsActivity extends AppCompatActivity {

    private Button loginButton;
    private Button findFriendsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_greetings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализация кнопок
        loginButton = findViewById(R.id.login_button);
        findFriendsButton = findViewById(R.id.find_friends_button);

        // Обработка нажатия кнопки для входа с авторизацией
        loginButton.setOnClickListener(v -> {
            // Открытие экрана авторизации
            Intent loginIntent = new Intent(GreetingsActivity.this, AuthActivity.class);
            startActivity(loginIntent);
        });

        // Обработка нажатия кнопки для поиска друзей
        findFriendsButton.setOnClickListener(v -> {
            Intent findFriendsIntent = new Intent(GreetingsActivity.this, MainActivity.class);
            startActivity(findFriendsIntent);
        });
    }
}
