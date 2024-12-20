package com.example.pintagram.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.data.AppDatabase;
import com.example.data.AcceptedFriendDao;
import com.example.data.AcceptedFriendRepositoryImpl;
import com.example.data.FriendRepositoryImpl;
import com.example.domain.Friend;
import com.example.domain.AcceptedFriendRepository;
import com.example.domain.FriendRepository;
import com.example.pintagram.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private FriendRepository friendRepository;
    private AcceptedFriendRepository acceptedFriendRepository;
    private FriendViewModel friendViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_friend_requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Инициализация базы данных и DAO для принятых друзей
        AppDatabase appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "friends-db").build();
        AcceptedFriendDao acceptedFriendDao = appDatabase.acceptedFriendDao();

        // Инициализация репозиториев
        friendRepository = new FriendRepositoryImpl();
        acceptedFriendRepository = new AcceptedFriendRepositoryImpl(acceptedFriendDao);  // Репозиторий принятых друзей

        // Инициализация ViewModel
        friendViewModel = new ViewModelProvider(this, new FriendViewModelFactory(acceptedFriendRepository)).get(FriendViewModel.class);

        // Инициализация адаптера
        friendAdapter = new FriendAdapter(new ArrayList<>(), friendViewModel, false);  // Начальное состояние с пустым списком
        recyclerView.setAdapter(friendAdapter);

        // Загружаем принятых друзей
        loadAcceptedFriends();

        return view;
    }

    private void loadAcceptedFriends() {
        friendRepository.getFriendsAsync(new FriendRepository.FriendCallback() {
            @Override
            public void onSuccess(List<Friend> friends) {
                if (friends != null) {
                    // Логируем количество загруженных принятых друзей
                    Log.d("HomeFragment", "Loaded " + friends.size() + " accepted friends");

                    // Обновляем UI с новым списком
                    requireActivity().runOnUiThread(() -> friendAdapter.updateFriends(friends));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("HomeFragment", "Failed to load accepted friends: " + t.getMessage());
            }
        });
    }
}
