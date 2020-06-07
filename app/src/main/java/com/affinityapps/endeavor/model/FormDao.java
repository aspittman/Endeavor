package com.affinityapps.endeavor.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FormDao {

    @Insert
    void insert(Form form);

    @Update
    void update(Form form);

    @Delete
    void delete(Form form);

    @Query("DELETE FROM volunteer_table")
    void deleteAllForms();

    @Query("SELECT * FROM volunteer_table ORDER BY date DESC")
    LiveData<List<Form>> getAllForms();
}
