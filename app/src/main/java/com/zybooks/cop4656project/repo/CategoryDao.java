package com.zybooks.cop4656project.repo;

import androidx.room.Dao;
import androidx.room.Insert;

import com.zybooks.cop4656project.models.Category;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);
}

