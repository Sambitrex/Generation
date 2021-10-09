package com.read.generation.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HistoryItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "result")
    public String result;

    @ColumnInfo(name = "date")
    public String date;
}

