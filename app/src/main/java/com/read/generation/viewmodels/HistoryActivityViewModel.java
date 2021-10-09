package com.read.generation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.read.generation.database.HistoryDatabase;
import com.read.generation.database.HistoryItem;

import java.util.List;

public class HistoryActivityViewModel extends AndroidViewModel {
    private final MutableLiveData<List<HistoryItem>> historyItemList;
    private final HistoryDatabase historyDatabase;


    public HistoryActivityViewModel(@NonNull Application application) {
        super(application);

        historyItemList = new MutableLiveData<>();
        historyDatabase = HistoryDatabase.getDatabaseInstance(
                getApplication().getApplicationContext()
        );
        getAllHistoryItems();
    }

    public MutableLiveData<List<HistoryItem>> getHistoryItemsData() {
        return historyItemList;
    }

    public void deleteHistoryItem(HistoryItem item) {
        historyDatabase.historyDao().deleteHistoryItem(item);
        getAllHistoryItems();
    }

    public void deleteAllHistoryItems() {
        historyDatabase.historyDao().deleteAllHistoryItems();
        getAllHistoryItems();
    }


    private void getAllHistoryItems() {
        List<HistoryItem> listOfItems = historyDatabase.historyDao().getAllHistoryItems();

        if (listOfItems.size() > 0) {
            historyItemList.setValue(listOfItems);
        } else {
            historyItemList.setValue(null);
        }
    }


}
