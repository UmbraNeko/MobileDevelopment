package com.example.data;

import androidx.lifecycle.LiveData;

import com.example.domain.Friend;
import com.example.domain.AcceptedFriendRepository;
import com.example.data.model.AcceptedFriendEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AcceptedFriendRepositoryImpl implements AcceptedFriendRepository {
    private final AcceptedFriendDao acceptedFriendDao;

    public AcceptedFriendRepositoryImpl(AcceptedFriendDao acceptedFriendDao) {
        this.acceptedFriendDao = acceptedFriendDao;
    }

    @Override
    public void addAcceptedFriend(Friend friend) {
        // Используем конструктор без ID, так как Room сам генерирует ID
        AcceptedFriendEntity entity = new AcceptedFriendEntity(
                friend.getName(),
                friend.getAge(),
                friend.getStatus(),
                friend.getAvatarUrl()
        );
        acceptedFriendDao.insert(entity);
    }

    @Override
    public List<Friend> getAllAcceptedFriends() {
        // Получение всех принятых друзей из базы данных
        return acceptedFriendDao.getAllAcceptedFriends();
    }

    @Override
    public int getAcceptedFriendCount() {
        return acceptedFriendDao.getAllAcceptedFriendsCount();  // Получаем количество принятых друзей
    }

    @Override
    public void deleteAcceptedFriend(String friendId) {
        acceptedFriendDao.deleteAcceptedFriend(Integer.parseInt(friendId));
    }

    @Override
    public boolean isAccepted(String friendId) {
        AcceptedFriendEntity entity = acceptedFriendDao.getAcceptedFriendById(Integer.parseInt(friendId));
        return entity != null;
    }
}
