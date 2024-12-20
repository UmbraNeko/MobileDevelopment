package com.example.data;

import android.util.Log;
import com.example.domain.Friend;
import com.example.domain.FriendRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class FriendRepositoryImpl implements FriendRepository {

    private static final String TAG = "FriendRepositoryImpl"; // Тег для логирования

    @Override
    public void getFriendsAsync(FriendCallback callback) {
        // Асинхронный вызов через Retrofit
        NetworkApi.FriendService service = NetworkApi.getClient().create(NetworkApi.FriendService.class);
        Call<List<Friend>> call = service.getFriends(); // Мы ожидаем список друзей

        Log.d(TAG, "Sending request to get Friends"); // Логируем начало запроса

        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Данные успешно получены, передаем их в callback
                    Log.d(TAG, "Friends successfully retrieved, count: " + response.body().size()); // Логируем успешный ответ
                    callback.onSuccess(response.body());
                } else {
                    // Ответ не успешен
                    Log.e(TAG, "Response unsuccessful, code: " + response.code()); // Логируем код ошибки
                    callback.onFailure(new Exception("Response unsuccessful"));
                }
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                // Ошибка при вызове API
                Log.e(TAG, "API call failed", t); // Логируем ошибку
                callback.onFailure(t);
            }
        });
    }
}
