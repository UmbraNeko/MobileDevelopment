package com.example.pintagram;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.LoginUser;
import com.example.domain.RegisterUser;

public class AuthViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application application;
    private final LoginUser loginUser;
    private final RegisterUser registerUser;

    public AuthViewModelFactory(Application application, LoginUser loginUser, RegisterUser registerUser) {
        this.application = application;
        this.loginUser = loginUser;
        this.registerUser = registerUser;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AuthViewModel(application, loginUser, registerUser);
    }
}

