package com.example.pintagram.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.data.AcceptedFriendRepositoryImpl;
import com.example.data.AppDatabase;
import com.example.data.AcceptedFriendDao;
import com.example.data.FriendRepositoryImpl;
import com.example.data.UserRepository;
import com.example.domain.AcceptedFriendRepository;
import com.example.domain.UserRepositoryInterface;
import com.example.domain.FriendRepository;
import com.example.pintagram.R;

public class SlideshowFragment extends Fragment {

    private TextView emailTextView;
    private TextView nameTextView;
    private TextView friendsCountTextView;
    private ImageButton menuButton;
    private SlideshowViewModel slideshowViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        UserRepositoryInterface userRepository = new UserRepository(getContext());

        AppDatabase db = AppDatabase.getInstance(getContext());

        AcceptedFriendDao friendDao = db.acceptedFriendDao();

        AcceptedFriendRepository friendRepository = new AcceptedFriendRepositoryImpl(friendDao);

        slideshowViewModel = new ViewModelProvider(this, new SlideshowViewModelFactory(userRepository, friendRepository))
                .get(SlideshowViewModel.class);

        emailTextView = view.findViewById(R.id.tv_profile_email);
        nameTextView = view.findViewById(R.id.tv_profile_name);
        friendsCountTextView = view.findViewById(R.id.tv_profile_friends_count);

        // Observe changes in email, name, and friends count
        slideshowViewModel.getUserEmail().observe(getViewLifecycleOwner(), email -> emailTextView.setText(email));
        slideshowViewModel.getUserName().observe(getViewLifecycleOwner(), name -> nameTextView.setText(name));
        slideshowViewModel.getFriendsCount().observe(getViewLifecycleOwner(), count -> friendsCountTextView.setText("Число друзей: " + count));
    }

    // Method to create a new instance of this fragment
    public static SlideshowFragment newInstance() {
        return new SlideshowFragment();
    }
}
