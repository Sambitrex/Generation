package com.read.generation.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.read.generation.R;
import com.read.generation.database.HistoryDatabase;
import com.read.generation.database.HistoryItem;

import java.util.Locale;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<HistoryItem> lastAdded;
    private HistoryDatabase database;
    private String[] monthNames;
    private String[] generations;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        Context context = getApplication().getApplicationContext();

        lastAdded = new MutableLiveData<>();
        database = HistoryDatabase.getDatabaseInstance(context);
        monthNames = context.getResources().getStringArray(R.array.month_names);
        generations = context.getResources().getStringArray(R.array.generations);
    }

    public MutableLiveData<HistoryItem> getItemData() {
        return lastAdded;
    }

    public void insertCategory(int month, int day, int year) {
        HistoryItem item = new HistoryItem();

        item.result = getResult(year);
        item.date = getFormattedDate(month, day, year);

        database.historyDao().insertHistoryItem(item);
        lastAdded.setValue(item);
    }

    public void setLastAddedHistoryItem() {
        lastAdded.setValue(database.historyDao().getLastAddedHistoryItem());
    }

    private String getFormattedDate(int month, int day, int year) {
        if(Locale.getDefault().getLanguage().equals("ru")) {
            return day + " " + monthNames[month] + " " + year;
        } else {
            return monthNames[month] + " " + day + " " + year;
        }
    }

    private String getResult(int year) {
        if (year >= 1946 && year <= 1964) {
            return generations[0];
        } else if (year >= 1965 && year <= 1980) {
            return generations[1];
        } else if (year >= 1981 && year <= 1996) {
            return generations[2];
        } else if (year >= 1997 && year <= 2012) {
            return generations[3];
        } else {
            return "No result";
        }
    }
}
