package com.example.fragmentapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Создаем Bundle и передаем номер студента
            Bundle bundle = new Bundle();
            bundle.putInt("my_number_student", 22);

            // Создаем новый экземпляр фрагмента и передаем аргументы
            BlankFragment fragment = new BlankFragment();
            fragment.setArguments(bundle);

            // Добавляем фрагмент
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, fragment)
                    .commit();
        }
    }
}
