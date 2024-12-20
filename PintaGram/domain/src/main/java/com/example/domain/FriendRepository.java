package com.example.domain;

import com.example.domain.Friend;

import java.util.List;

public interface FriendRepository {
    void getFriendsAsync(FriendCallback callback);

    interface FriendCallback {
        void onSuccess(List<Friend> friends); // Теперь передается список друзей
        void onFailure(Throwable t);
    }
}
