package com.gsmarena.firstsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView mRcv;
    private ListAdapter adapter;
    private LinkedList<String> mLinkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mLinkedList = new LinkedList<>();

        mRcv = findViewById(R.id.rcv);
        adapter = new ListAdapter(this, createList(40));
        mRcv.setAdapter(adapter);
        mRcv.setLayoutManager(new LinearLayoutManager(this));
    }

    private LinkedList<String> createList(int amount) {
        for (int i = 0; i < amount; i++) {
            mLinkedList.add("item " + i);
        }
        return mLinkedList;
    }

}
