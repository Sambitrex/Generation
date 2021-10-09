package com.read.generation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.read.generation.R;
import com.read.generation.database.HistoryItem;
import com.read.generation.recyclerview.HistoryItemAdapter;
import com.read.generation.viewmodels.HistoryActivityViewModel;

import java.util.List;
public class HistoryActivity extends AppCompatActivity {
    private HistoryActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private HistoryItemAdapter historyItemAdapter;
    private TextView textViewNoItems;

    private HandleDeleteClick deleteHandler = item -> {
        viewModel.deleteHistoryItem(item);
    };

    private Observer<List<HistoryItem>> observer = historyItems -> {
        if (historyItems != null) {
            historyItemAdapter.setHistoryItemsList(historyItems);
        } else {
            textViewNoItems.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerViewHistory);
        textViewNoItems = findViewById(R.id.textViewNoItems);

        initViewModel();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        viewModel.deleteAllHistoryItems();
        return true;
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(HistoryActivityViewModel.class);
        viewModel.getHistoryItemsData().observe(this, observer);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyItemAdapter = new HistoryItemAdapter(this, deleteHandler);
        recyclerView.setAdapter(historyItemAdapter);
    }

    public interface HandleDeleteClick {
        void deleteHistoryItem(HistoryItem item);
    }
}