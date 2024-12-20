package com.example.pintagram.ui.home;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.AcceptedFriendRepository;

public class FriendViewModelFactory implements ViewModelProvider.Factory {
    private final AcceptedFriendRepository acceptedFriendRepository;

    public FriendViewModelFactory(AcceptedFriendRepository acceptedFriendRepository) {
        this.acceptedFriendRepository = acceptedFriendRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FriendViewModel.class)) {
            return (T) new FriendViewModel(acceptedFriendRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
