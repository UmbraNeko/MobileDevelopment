package com.example.pintagram.ui.home;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.Friend;
import com.example.domain.AcceptedFriendRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FriendViewModel extends ViewModel {
    private final AcceptedFriendRepository acceptedFriendRepository;
    private static final String TAG = "FriendViewModel";
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final MutableLiveData<List<Friend>> acceptedFriends = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFriendLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> friendCount = new MutableLiveData<>();

    public FriendViewModel(AcceptedFriendRepository acceptedFriendRepository) {
        this.acceptedFriendRepository = acceptedFriendRepository;
    }

    // Получение списка принятых друзей
    public LiveData<List<Friend>> getAcceptedFriends() {
        executorService.execute(() -> {
            List<Friend> friends = acceptedFriendRepository.getAllAcceptedFriends();
            // Обновляем LiveData с результатом на главном потоке
            new Handler(Looper.getMainLooper()).post(() -> acceptedFriends.setValue(friends));
        });
        return acceptedFriends;
    }

    // Добавление друга в список принятых
    public void addAcceptedFriend(Friend friend) {
        executorService.execute(() -> {
            acceptedFriendRepository.addAcceptedFriend(friend);
        });
    }

    // Получаем количество принятых друзей
    public LiveData<Integer> getFriendCount() {
        executorService.execute(() -> {
            int count = acceptedFriendRepository.getAcceptedFriendCount();
            new Handler(Looper.getMainLooper()).post(() -> friendCount.setValue(count));
        });
        return friendCount;
    }

    // Удаление друга из списка принятых
    public void deleteAcceptedFriend(String friendId) {
        executorService.execute(() -> {
            acceptedFriendRepository.deleteAcceptedFriend(friendId);
        });
    }

    // Проверка, является ли пользователь другом
    public boolean checkIfFriend(String friendId) {
        return acceptedFriendRepository.isAccepted(friendId);
    }

    // Проверка, является ли пользователь другом с использованием LiveData
    public LiveData<Boolean> isFriend(String friendId) {
        executorService.execute(() -> {
            boolean isFriend = acceptedFriendRepository.isAccepted(friendId);
            // Обновляем LiveData с результатом на главном потоке
            new Handler(Looper.getMainLooper()).post(() -> isFriendLiveData.setValue(isFriend));
        });
        return isFriendLiveData;
    }
}

