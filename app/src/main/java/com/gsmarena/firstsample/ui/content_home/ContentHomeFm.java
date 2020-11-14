package com.gsmarena.firstsample.ui.content_home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.callback.EndlessRecyclerViewScrollListener;
import com.gsmarena.firstsample.retrofit2.item.Datum;
import com.gsmarena.firstsample.ui.UsersViewModel;
import com.gsmarena.firstsample.ui.adapter.ContentHomeAdapter;

import java.util.LinkedList;
import java.util.List;

public class ContentHomeFm extends Fragment {


    public static final String TAG = ContentHomeFm.class.getSimpleName();
    private RecyclerView mRcv;
    private ContentHomeAdapter adapter;
    private LinkedList<String> mLinkedList;
    private EndlessRecyclerViewScrollListener mListener;
    private LinearLayoutManager mLinearLayoutManager;

    //private APIUserInterface mApiUserInterface;
    private Gson mGson;

    private UsersViewModel mUsersViewModel;


    public ContentHomeFm() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (getActivity() == null)
            return;

        mUsersViewModel = new ViewModelProvider(getActivity()).get(UsersViewModel.class);
        mUsersViewModel.getUserByPage("1");

        mLinkedList = new LinkedList<>();

        mLinearLayoutManager = new LinearLayoutManager(getContext());

        mRcv = view.findViewById(R.id.rcv);
        adapter = new ContentHomeAdapter(getContext());
        mRcv.setAdapter(adapter);
        mRcv.setLayoutManager(mLinearLayoutManager);

        mRcv.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        mGson = new Gson();

        mUsersViewModel.getUserRecords().observe(getActivity(), new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> data) {
                for (Datum item : data) {
                    adapter.insertItem(item);
                }
                Log.d(TAG, mGson.toJson(data));
            }
        });

        mListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mUsersViewModel.getUserByPage(String.valueOf(page + 1));
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
