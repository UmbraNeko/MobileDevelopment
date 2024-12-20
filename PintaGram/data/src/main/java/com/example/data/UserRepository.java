package com.example.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.domain.UserRepositoryInterface;

public class UserRepository implements UserRepositoryInterface {
    private SharedPreferences sharedPreferences;

    public UserRepository(Context context) {
        sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString("email", "No Email");
    }

    @Override
    public String getName() {
        return sharedPreferences.getString("name", "No Name");
    }
}

