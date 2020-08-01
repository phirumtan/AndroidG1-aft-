package com.gsmarena.firstsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsmarena.firstsample.callback.EndlessRecyclerViewScrollListener;

import java.util.LinkedList;

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView mRcv;
    private ListAdapter adapter;
    private LinkedList<String> mLinkedList;
    private EndlessRecyclerViewScrollListener mListener;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mLinkedList = new LinkedList<>();

        mLinearLayoutManager = new LinearLayoutManager(this);

        mRcv = findViewById(R.id.rcv);
        adapter = new ListAdapter(this, createList(40));
        mRcv.setAdapter(adapter);
        mRcv.setLayoutManager(mLinearLayoutManager);

        mListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                int currentSize = adapter.getItemCount();
                for (int i = 0; i < 10; i++) {
                    adapter.insertItem("item " + (adapter.getItemCount() + i));
                }
                adapter.notifyItemRangeChanged(currentSize, adapter.getItemCount() - 1);
            }
        };

        mRcv.addOnScrollListener(mListener);

    }

    private LinkedList<String> createList(int amount) {
        for (int i = 0; i < amount; i++) {
            mLinkedList.add("item " + i);
        }
        return mLinkedList;
    }

}
