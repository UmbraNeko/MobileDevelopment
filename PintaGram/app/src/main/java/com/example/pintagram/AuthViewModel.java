package com.example.pintagram;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;

import com.example.domain.AuthRepository;
import com.example.domain.LoginUser;
import com.example.domain.RegisterUser;

public class AuthViewModel extends AndroidViewModel {

    private final LoginUser loginUser;
    private final RegisterUser registerUser;

    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AuthViewModel(Application application, LoginUser loginUser, RegisterUser registerUser) {
        super(application);
        this.loginUser = loginUser;
        this.registerUser = registerUser;
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void login(String email, String password) {
        loginUser.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                loginSuccess.postValue(true);
            }

            @Override
            public void onFailure(String error) {
                errorMessage.postValue("Login failed: " + error); // Обработка ошибки
            }
        });
    }

    public void register(String name, String email, String password) {
        registerUser.execute(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                // Сохраняем данные пользователя, включая имя
                saveUserData(name, email);
            }

            @Override
            public void onFailure(String error) {
                errorMessage.postValue("Register failed: " + error);
            }
        });
    }

    private void saveUserData(String name, String email) {
        // Сохраняем имя и email в SharedPreferences
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("UserPrefs", Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);  // Сохраняем имя
        editor.putString("email", email);  // Сохраняем email
        editor.putBoolean("isLoggedIn", true);  // Устанавливаем флаг, что пользователь авторизован
        editor.apply();
    }
}
