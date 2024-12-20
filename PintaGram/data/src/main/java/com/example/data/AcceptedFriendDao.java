package com.example.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.data.model.AcceptedFriendEntity;
import com.example.domain.Friend;

import java.util.List;

@Dao
public interface AcceptedFriendDao {
    // Вставка нового друга в базу
    @Insert
    void insert(AcceptedFriendEntity friend);

    @Query("SELECT * FROM accepted_friends")
    List<Friend> getAllAcceptedFriends();

    // Удалить друга из списка по его ID
    @Query("DELETE FROM accepted_friends WHERE id = :friendId")
    void deleteAcceptedFriend(int friendId);

    // Получить друга по его имени
    @Query("SELECT * FROM accepted_friends WHERE name = :name LIMIT 1")
    AcceptedFriendEntity getAcceptedFriendByName(String name);

    // Получить друга по его ID
    @Query("SELECT * FROM accepted_friends WHERE id = :id LIMIT 1")
    AcceptedFriendEntity getAcceptedFriendById(int id);

    @Query("SELECT COUNT(*) FROM accepted_friends")
    int getAllAcceptedFriendsCount();
}
