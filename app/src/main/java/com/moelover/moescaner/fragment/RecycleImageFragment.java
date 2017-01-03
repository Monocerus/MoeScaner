package com.moelover.moescaner.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moelover.moescaner.R;
import com.moelover.moescaner.activity.ImageDetailActivity;
import com.moelover.moescaner.adaptor.RecyclerCharacterAdapter;


/*
 * Created by tianle on 2016/2/27.
 */
public class RecycleImageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerCharacterAdapter mRecyclerCharacterAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content_main, container, false);
        mRecyclerCharacterAdapter = new RecyclerCharacterAdapter(getActivity());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycle_images);
        mRecyclerView.setAdapter(mRecyclerCharacterAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        //设置刷新的时候底部view显示成一行
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mRecyclerCharacterAdapter.getItemViewType(position)){
                    case RecyclerCharacterAdapter.TYPE_CONTENT:
                        return 1;
                    case RecyclerCharacterAdapter.TYPE_LOAD_MORE:
                        return 3;
                    default:
                        return 1;
                }
            }
        });

        mRecyclerCharacterAdapter.setmOnItemClickListener(new RecyclerCharacterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
                intent.putExtra(ImageDetailActivity.EXTRA_IMAGE_POSITION,position);
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.google_red,R.color.google_yellow,R.color.google_green,R.color.google_blue);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isSlideToBottom(recyclerView)) {
                    //滑到底部加载更多,此时会设置一个标记为用来判断当前是不是处于更新状态，如果处于更新状态则无法刷新和再次更新

                    if (!mRecyclerCharacterAdapter.isBeingUpdated()) { //当前处于未更新状态
                        //显示更新状态
                        //设置无法显示下拉刷新
                        mSwipeRefreshLayout.setEnabled(false);
                        mRecyclerCharacterAdapter.loadMorePageImagerView(mSwipeRefreshLayout);

                    }
                }
            }
        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);

                //并进行刷新
                mRecyclerCharacterAdapter.loadFirstPageImageView(mSwipeRefreshLayout);
            }
        });
        return rootView;
    }

    @Override
    public void onRefresh() {
        //刷新时候进行的操作
        mRecyclerCharacterAdapter.loadFirstPageImageView(mSwipeRefreshLayout);
    }

    public boolean isSlideToBottom(RecyclerView recyclerView) {

        return recyclerView != null &&
                recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                        >= recyclerView.computeVerticalScrollRange();
    }

}
