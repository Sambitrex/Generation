package com.read.generation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.read.generation.R;
import com.read.generation.database.HistoryItem;
import com.read.generation.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private DatePicker datePicker;
    private TextView textViewDate;
    private Button buttonSelectDate;
    private Button buttonHistory;

    private Observer<HistoryItem> historyItemObserver = item -> {
        if (item != null) {
            textViewDate.setText(item.result + "\n" + item.date);
        } else {
            textViewDate.setText("Select date");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonHistory = findViewById(R.id.buttonHistory);
        textViewDate = findViewById(R.id.textViewDate);
        
        buttonSelectDate.setOnClickListener(v -> {
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            int year = datePicker.getYear();
            viewModel.insertCategory(month, day, year);
        });

        buttonHistory.setOnClickListener(v -> {
            buttonHistory.setEnabled(false);
            buttonSelectDate.setEnabled(false);

            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
        
        initViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonHistory.setEnabled(true);
        buttonSelectDate.setEnabled(true);
        viewModel.setLastAddedHistoryItem();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getItemData().observe(this, historyItemObserver);
    }
}