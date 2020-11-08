package com.gsmarena.firstsample.ui.content_home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.callback.EndlessRecyclerViewScrollListener;
import com.gsmarena.firstsample.retrofit2.APIClient;
import com.gsmarena.firstsample.retrofit2.APIUserInterface;
import com.gsmarena.firstsample.retrofit2.item.Datum;
import com.gsmarena.firstsample.retrofit2.item.UserItem;
import com.gsmarena.firstsample.ui.adapter.ContentHomeAdapter;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentHomeFm extends Fragment {


    public static final String TAG = ContentHomeFm.class.getSimpleName();
    private RecyclerView mRcv;
    private ContentHomeAdapter adapter;
    private LinkedList<String> mLinkedList;
    private EndlessRecyclerViewScrollListener mListener;
    private LinearLayoutManager mLinearLayoutManager;

    private APIUserInterface mApiUserInterface;
    private Gson mGson;


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

        mLinkedList = new LinkedList<>();

        mLinearLayoutManager = new LinearLayoutManager(getContext());

        mRcv = view.findViewById(R.id.rcv);
        adapter = new ContentHomeAdapter(getContext());
        mRcv.setAdapter(adapter);
        mRcv.setLayoutManager(mLinearLayoutManager);

        mGson = new Gson();
        mApiUserInterface = APIClient.getClient().create(APIUserInterface.class);

        Call<UserItem> userItemCall = mApiUserInterface.doGetUserList("1");
        userItemCall.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                for (Datum item : response.body().getData()) {
                    adapter.insertItem(item);
                }
                Log.d(TAG, mGson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {

                Log.e(TAG, "error hey " + t.getMessage());
            }
        });

        /*mListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                int currentSize = adapter.getItemCount();
                for (int i = 0; i < 10; i++) {
                    adapter.insertItem("item " + (adapter.getItemCount() + i));
                }
                adapter.notifyItemRangeChanged(currentSize, adapter.getItemCount() - 1);
            }
        };*/

        //mRcv.addOnScrollListener(mListener);
    }

    private LinkedList<String> createList(int amount) {
        for (int i = 0; i < amount; i++) {
            mLinkedList.add("item " + i);
        }
        return mLinkedList;
    }
}
