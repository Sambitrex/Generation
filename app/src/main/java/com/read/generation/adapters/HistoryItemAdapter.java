package com.read.generation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.read.generation.R;
import com.read.generation.database.HistoryItem;
import com.read.generation.view.HistoryActivity;

import java.util.List;


public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.HistoryItemViewHolder> {
    private final Context context;
    private List<HistoryItem> historyItemsList;
    HistoryActivity.HandleDeleteClick deleteHandler;


    public HistoryItemAdapter(Context context, HistoryActivity.HandleDeleteClick deleteHandler) {
        this.context = context;
        this.deleteHandler = deleteHandler;
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_history_item,
                parent,
                false
        );
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        String result = historyItemsList.get(position).result;
        String date = historyItemsList.get(position).date;

        holder.textViewItemResult.setText(result);
        holder.textViewItemDate.setText(date);

        holder.imageDelete.setOnClickListener(v -> {
            deleteHandler.deleteHistoryItem(historyItemsList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return historyItemsList.size();
    }

    public void setHistoryItemsList(List<HistoryItem> historyItemsList) {
        this.historyItemsList = historyItemsList;
        notifyDataSetChanged();
    }

    public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItemResult;
        public TextView textViewItemDate;
        public ImageView imageDelete;

        public HistoryItemViewHolder(@NonNull View view) {
            super(view);

            textViewItemResult = view.findViewById(R.id.textViewItemResult);
            textViewItemDate = view.findViewById(R.id.textViewItemDate);
            imageDelete = view.findViewById(R.id.imageDelete);
        }
    }
}
