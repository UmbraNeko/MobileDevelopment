package com.example.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accepted_friends") // Имя таблицы "accepted_friends"
public class AcceptedFriendEntity {
    @PrimaryKey(autoGenerate = true) // ID будет генерироваться Room автоматически
    private int id;
    private String name;
    private String age;
    private String status;
    private String avatarUrl;

    // Пустой конструктор, необходим для Room
    public AcceptedFriendEntity() {
    }

    // Конструктор без id (для создания новых записей)
    public AcceptedFriendEntity(String name, String age, String status, String avatarUrl) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.avatarUrl = avatarUrl;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
