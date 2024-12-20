package com.example.domain;

import com.example.domain.AcceptedFriend;
import com.example.domain.Friend;

import java.util.List;

public interface AcceptedFriendRepository {
    void addAcceptedFriend(Friend friend);  // Добавление друга в принятые
    List<Friend> getAllAcceptedFriends();          // Получение всех принятых друзей
    void deleteAcceptedFriend(String friendId);    // Удаление друга из принятых по ID
    boolean isAccepted(String friendId);           // Проверка, является ли друг принятым
    int getAcceptedFriendCount();
}

