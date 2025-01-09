package com.example.bottomnavigationapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigationapp.R;

public class HomeFragment extends Fragment {

    private String[] fakeTracks = {
            "Track 1 - Artist A",
            "Track 2 - Artist B",
            "Track 3 - Artist C",
            "Track 4 - Artist D",
            "Track 5 - Artist E"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView trackListView = view.findViewById(R.id.trackListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, fakeTracks);
        trackListView.setAdapter(adapter);
        return view;
    }
}