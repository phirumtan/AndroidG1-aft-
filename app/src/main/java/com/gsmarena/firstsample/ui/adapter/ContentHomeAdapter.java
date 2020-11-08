package com.gsmarena.firstsample.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.retrofit2.item.Datum;

import java.util.LinkedList;

public class ContentHomeAdapter extends RecyclerView.Adapter<ContentHomeAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private LinkedList<Datum> mListItem;
    private Context mContext;

    public ContentHomeAdapter(Context context) {
        super();
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mListItem = new LinkedList<>();
    }

    public ContentHomeAdapter(Context context, LinkedList<Datum> listItem) {
        this.mContext = context;
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
        Datum item = mListItem.get(position);

        Glide.with(mContext).load(item.getAvatar()).into(holder.mImgIcon);

        holder.mTxtItemName.setText(item.getFirstName() + " " + item.getLastName());
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public void insertItem(Datum item) {
        this.mListItem.add(item);
        notifyItemInserted(mListItem.size());
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
