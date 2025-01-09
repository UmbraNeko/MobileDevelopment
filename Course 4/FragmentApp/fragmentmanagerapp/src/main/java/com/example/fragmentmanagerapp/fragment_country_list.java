package com.example.fragmentmanagerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class fragment_country_list extends Fragment {

    private ShareViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        ListView countryListView = view.findViewById(R.id.countryListView);

        // Пример данных
        String[] countries = {"USA", "Canada", "Mexico", "France", "Germany"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, countries);
        countryListView.setAdapter(adapter);

        countryListView.setOnItemClickListener((parent, v, position, id) -> {
            String selectedCountry = countries[position];
            viewModel.setSelectedCountry(selectedCountry); // Передаем данные через ViewModel
        });
    }
}