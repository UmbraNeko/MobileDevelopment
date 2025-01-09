package com.example.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements FragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Устанавливаем слушатель для получения результата
        getSupportFragmentManager().setFragmentResultListener(
                "requestKey",
                this,
                (requestKey, bundle) -> {
                    String result = bundle.getString("key");
                    Log.d(BottomSheetFragment.class.getSimpleName(), "I'm MainActivity: " + result);
                }
        );

        // Инициализация фрагментов
        initFragments();
    }

    private void initFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Добавляем DataFragment
        transaction.add(R.id.fragmentContainerData, new DataFragment());

        // Добавляем BottomSheetFragment
        transaction.add(R.id.fragmentContainerBottomSheet, new BottomSheetFragment());

        // Добавляем BlankFragment
        transaction.add(R.id.fragmentContainerImage, new BlankFragment());

        transaction.commit();
    }

    @Override
    public void sendResult(String message) {
        Log.d(MainActivity.class.getSimpleName(), "Message from fragment: " + message);
    }
}
