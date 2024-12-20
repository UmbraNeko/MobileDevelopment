package com.example.pintagram;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pintagram.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Скрыть стандартный ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Настройка Toolbar
        setSupportActionBar(binding.appBarMain.toolbar);

        // Логика для действия кнопки FAB
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

        // Настройка DrawerLayout и NavigationView
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Настройка AppBarConfiguration с ID фрагментов
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow) // Список ID фрагментов для верхнего уровня
                .setOpenableLayout(drawer) // Определение доступности Drawer
                .build();

        // Настройка NavController для управления навигацией
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Настройка кнопки навигации для отображения Drawer
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // Привязка NavigationView с NavController для обработки навигации
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Наполнение меню; добавление элементов в ActionBar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Логика для обработки кнопки назад или меню
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
