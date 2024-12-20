package com.example.pintagram.ui.slideshow;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.AcceptedFriendRepository;
import com.example.domain.FriendRepository;
import com.example.domain.UserRepositoryInterface;

public class SlideshowViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepositoryInterface userRepository;
    private final AcceptedFriendRepository friendRepository;

    public SlideshowViewModelFactory(UserRepositoryInterface userRepository, AcceptedFriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SlideshowViewModel.class)) {
            return (T) new SlideshowViewModel(userRepository, friendRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
