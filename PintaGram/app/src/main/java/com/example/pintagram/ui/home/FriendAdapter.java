package com.example.pintagram.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.Friend;
import com.example.pintagram.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<Friend> friends;
    private FriendViewModel friendViewModel;
    private boolean isFavoriteList;
    private static final String TAG = "FriendAdapter";

    public FriendAdapter(List<Friend> friends, FriendViewModel friendViewModel, boolean isFavoriteList) {
        this.friends = friends;
        this.friendViewModel = friendViewModel;
        this.isFavoriteList = isFavoriteList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = isFavoriteList ? R.layout.friend_item : R.layout.friend_request_item;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friends.get(position);

        holder.friendName.setText(friend.getName());
        holder.friendStatus.setText(friend.getStatus());
        Picasso.get().load(friend.getAvatarUrl()).into(holder.friendAvatar);

        // Оптимизация наблюдения за состоянием друга
        friendViewModel.isFriend(friend.getId()).removeObservers((LifecycleOwner) holder.itemView.getContext());
        friendViewModel.isFriend(friend.getId()).observe((LifecycleOwner) holder.itemView.getContext(), isFavorite -> {
            if (isFavoriteList) {
                // В списке избранных отображаем кнопку "Удалить"
                holder.actionButton.setImageResource(R.drawable.ic_remove);
                holder.actionButton.setOnClickListener(v -> {
                    Log.d(TAG, "Removing friend from favorites: " + friend.getName());
                    friendViewModel.deleteAcceptedFriend(friend.getId());
                });
            } else {
                // В общем списке отображаем кнопку "Добавить"
                holder.actionButton.setImageResource(isFavorite ? R.drawable.ic_remove : R.drawable.ic_add);
                holder.actionButton.setOnClickListener(v -> {
                    if (isFavorite) {
                        Log.d(TAG, "Removing friend from favorites: " + friend.getName());
                        friendViewModel.deleteAcceptedFriend(friend.getId());
                    } else {
                        Log.d(TAG, "Adding friend to favorites: " + friend.getName());
                        friendViewModel.addAcceptedFriend(friend);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends != null ? friends.size() : 0;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
        notifyDataSetChanged();
    }

    public void updateFriends(List<Friend> newFriends) {
        this.friends = newFriends;
        notifyDataSetChanged();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView friendAvatar;
        TextView friendName;
        TextView friendStatus;
        ImageButton actionButton;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            friendAvatar = itemView.findViewById(R.id.iv_request_avatar);
            friendName = itemView.findViewById(R.id.tv_request_name);
            friendStatus = itemView.findViewById(R.id.tv_request_status);
            actionButton = itemView.findViewById(R.id.btn_add_friend); // Универсальная кнопка
        }
    }
}
