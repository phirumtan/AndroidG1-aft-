package com.gsmarena.firstsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private LinkedList<String> mListItem;

    public ListAdapter() {
        super();
    }

    public ListAdapter(Context context, LinkedList<String> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.mListItem = listItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.layout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTxtItemName.setText(mListItem.get(position));
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView mImgIcon;
        private AppCompatTextView mTxtItemName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgIcon = itemView.findViewById(R.id.img_icon);
            mTxtItemName = itemView.findViewById(R.id.txt_item_name);

        }
    }
}
