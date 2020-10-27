package com.example.roomapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    void addUser(User user);

    @Query("SELECT * FROM users")
    List<User> getAll();

}
