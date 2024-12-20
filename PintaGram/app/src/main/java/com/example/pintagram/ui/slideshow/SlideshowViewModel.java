package com.example.pintagram.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.data.AcceptedFriendDao;
import com.example.domain.AcceptedFriendRepository;
import com.example.domain.FriendRepository;
import com.example.domain.UserRepositoryInterface;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> userEmail = new MutableLiveData<>();
    private final MutableLiveData<String> userName = new MutableLiveData<>();
    private final MutableLiveData<Integer> friendsCount = new MutableLiveData<>();

    private final UserRepositoryInterface userRepository;
    private final AcceptedFriendRepository friendRepository;

    public SlideshowViewModel(UserRepositoryInterface userRepository, AcceptedFriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;

        // Инициализация данных
        loadUserData();
        loadFriendsCount();
    }

    // Метод для загрузки данных пользователя
    private void loadUserData() {
        // Пример получения email и имени пользователя
        String email = userRepository.getEmail();
        String name = userRepository.getName();

        userEmail.setValue(email);
        userName.setValue(name);
    }

    // Метод для загрузки количества друзей
    private void loadFriendsCount() {
        new Thread(() -> {
            int count = friendRepository.getAcceptedFriendCount();
            friendsCount.postValue(count);
        }).start();
    }

    // Геттеры
    public LiveData<String> getUserEmail() {
        return userEmail;
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<Integer> getFriendsCount() {
        return friendsCount;
    }
}
