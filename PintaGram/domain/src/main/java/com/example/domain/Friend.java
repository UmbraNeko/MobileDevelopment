package com.example.domain;

public class Friend {
    private String id;          // Уникальный идентификатор друга
    private String name;        // Имя друга
    private String age;         // Возраст друга
    private String status;      // Статус друга
    private String avatarUrl;   // URL аватара друга

    // Конструктор с параметрами
    public Friend(String id, String name, String age, String status, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
        this.avatarUrl = avatarUrl;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

