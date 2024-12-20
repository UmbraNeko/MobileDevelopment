package com.example.pintagram.ui.gallery;

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

import com.example.data.AppDatabase;
import com.example.data.AcceptedFriendDao;
import com.example.data.AcceptedFriendRepositoryImpl;
import com.example.domain.Friend;
import com.example.domain.AcceptedFriendRepository;
import com.example.pintagram.R;
import com.example.pintagram.ui.home.FriendAdapter;
import com.example.pintagram.ui.home.FriendViewModel;
import com.example.pintagram.ui.home.FriendViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    private RecyclerView favoriteFriendRecyclerView;
    private FriendAdapter friendAdapter;
    private AcceptedFriendDao acceptedFriendDao;
    private AcceptedFriendRepository acceptedFriendRepository;
    private FriendViewModel friendViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Создаем разметку для фрагмента
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Инициализация DAO
        acceptedFriendDao = AppDatabase.getInstance(requireContext()).acceptedFriendDao();

        // Инициализация репозитория
        acceptedFriendRepository = new AcceptedFriendRepositoryImpl(acceptedFriendDao);

        // Инициализация ViewModel
        friendViewModel = new ViewModelProvider(this, new FriendViewModelFactory(acceptedFriendRepository)).get(FriendViewModel.class);

        // Инициализация RecyclerView
        favoriteFriendRecyclerView = view.findViewById(R.id.rv_friend_list);
        favoriteFriendRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Инициализация адаптера с пустым списком друзей
        friendAdapter = new FriendAdapter(new ArrayList<>(), friendViewModel, true);
        favoriteFriendRecyclerView.setAdapter(friendAdapter);

        // Загрузка избранных друзей через репозиторий
        loadFavoriteFriends();

        // Подписка на LiveData для обновления данных
        friendViewModel.getAcceptedFriends().observe(getViewLifecycleOwner(), friends -> {
            if (friends != null && !friends.isEmpty()) {
                // Обновляем данные в адаптере
                friendAdapter.updateFriends(friends);
            } else {
                Log.d("GalleryFragment", "No favorite friends found");
            }
        });

        return view;
    }

    private void loadFavoriteFriends() {
        friendViewModel.getAcceptedFriends().observe(getViewLifecycleOwner(), friends -> {
            if (friends != null && !friends.isEmpty()) {
                // Обновляем данные в адаптере
                friendAdapter.updateFriends(friends);
            } else {
                Log.d("GalleryFragment", "No favorite friends found");
            }
        });
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }
}
