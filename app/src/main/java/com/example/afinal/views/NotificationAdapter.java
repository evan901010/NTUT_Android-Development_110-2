package com.example.afinal.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;

import java.util.ArrayList;
import java.util.Collections;

public class NotificationAdapter extends
        RecyclerView.Adapter<NotificationAdapter.WordViewHolder> implements ItemTouchHelperAdapter{
    private ArrayList<ArrayList<String>> notificationList;
    private final LayoutInflater mInflater;

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(notificationList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        notificationList.remove(position);
        notifyItemRemoved(position);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleView;
        public final TextView textView;
        final NotificationAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, NotificationAdapter adapter) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            textView = itemView.findViewById(R.id.text);
            this.mAdapter = adapter;
        }
    }

    public NotificationAdapter(Context context, ArrayList<ArrayList<String>> notificationList) {
        mInflater = LayoutInflater.from(context);
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(
                R.layout.notification_row, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.WordViewHolder holder, int position) {
        holder.titleView.setText(notificationList.get(position).get(1));
        holder.textView.setText(notificationList.get(position).get(2));
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
