package com.read.generation.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM HistoryItem")
    List<HistoryItem> getAllHistoryItems();

    @Insert
    void insertHistoryItem(HistoryItem item);

    @Delete
    void deleteHistoryItem(HistoryItem item);

    @Query("DELETE FROM HistoryItem")
    void deleteAllHistoryItems();

    @Query("SELECT * FROM HistoryItem ORDER BY id DESC LIMIT 1")
    HistoryItem getLastAddedHistoryItem();
}
