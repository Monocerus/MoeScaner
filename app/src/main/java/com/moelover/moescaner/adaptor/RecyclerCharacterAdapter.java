package com.moelover.moescaner.adaptor;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.moelover.moescaner.ApplicationController;
import com.moelover.moescaner.R;
import com.moelover.moescaner.model.ImageModelYande;
import com.moelover.moescaner.model.ImageViewArray;
import com.moelover.moescaner.net.GsonRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class RecyclerCharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_CONTENT = 0;
    public static final int TYPE_LOAD_MORE = 1;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int pageNumber = 1;
    private ImageViewArray imageViews;
    private String strUri = "https://yande.re/post.json?";
    private String strPage = "page=";
    private boolean beingUpdated = false;
    private boolean beingRefresh = false;

    public boolean isBeingRefresh() {
        return beingRefresh;
    }

    public boolean isBeingUpdated() {
        return beingUpdated;
    }


    public void setBeingUpdatedState(boolean state) {
        this.beingUpdated = state;
    }

    public void setBeingRefreshState(boolean state) { this.beingRefresh = state; }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public RecyclerCharacterAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        imageViews = ApplicationController.getInstance().getImageViewArray();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CONTENT:
                return new RecyclerCharacterViewHolder(mLayoutInflater.inflate(R.layout.layout_recycler_item, parent, false));
            case TYPE_LOAD_MORE:
                return new RecyclerFootViewHolder(mLayoutInflater.inflate(R.layout.loader_more_footer, parent, false));
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //设置item参数的值
        if (holder instanceof RecyclerCharacterViewHolder) {
            ImageLoader.getInstance().displayImage(imageViews.getImages().get(position).getPreview_url(),
                    ((RecyclerCharacterViewHolder)holder).imageView, ApplicationController.getInstance().getOptions());

            //设置点击事件
            if(mOnItemClickListener != null) {
                ((RecyclerCharacterViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(((RecyclerCharacterViewHolder) holder).imageView,position);
                    }
                });
            }
        } else if (holder instanceof RecyclerFootViewHolder) {
            //如果正在刷新则不显示
            if(isBeingRefresh()) {
                ((RecyclerFootViewHolder)holder).itemView.setVisibility(View.GONE);
            } else {
                ((RecyclerFootViewHolder)holder).itemView.setVisibility(View.VISIBLE);
            }
            //否则显示
        }

        //设置imageview
    }


    @Override
    public int getItemCount() {
        int itemCount = imageViews.getImages().size();
        //第一次进入的时候，count=0，此时不显示footview，只有count>0时才能显示
        return  itemCount > 0 ? itemCount+1 : itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return position == imageViews.getImages().size() ? TYPE_LOAD_MORE : TYPE_CONTENT;
    }


    private class RecyclerCharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        RecyclerCharacterViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_imageContent);
        }
    }

    private class RecyclerFootViewHolder extends RecyclerView.ViewHolder {

        public RecyclerFootViewHolder(View itemView) {
            super(itemView);
        }
    }


    public void loadFirstPageImageView(final SwipeRefreshLayout swipeRefreshLayout) {
        pageNumber = 1;
        GsonRequest<ArrayList<ImageModelYande>> request = new GsonRequest<>(strUri + strPage + pageNumber, new TypeToken<ArrayList<ImageModelYande>>() {
        }.getType(),
                new Response.Listener<ArrayList<ImageModelYande>>() {
                    @Override
                    public void onResponse(ArrayList<ImageModelYande> response) {
                        imageViews.setImages(response);
                        notifyDataSetChanged();
                        setBeingUpdatedState(false);
                        setBeingRefreshState(false);
                        //更新刷新状态
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setBeingUpdatedState(false);
                setBeingRefreshState(false);
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(mContext, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
        setBeingUpdatedState(true);
        setBeingRefreshState(true);
        ApplicationController.getInstance().addToRequestQueue(request);
    }

    public void loadMorePageImagerView(final SwipeRefreshLayout swipeRefreshLayout) {
        pageNumber++;
        GsonRequest<ArrayList<ImageModelYande>> request = new GsonRequest<>(strUri + strPage + pageNumber, new TypeToken<ArrayList<ImageModelYande>>() {
        }.getType(),
                new Response.Listener<ArrayList<ImageModelYande>>() {
                    @Override
                    public void onResponse(ArrayList<ImageModelYande> response) {
                        int position = imageViews.getImages().size();
                        imageViews.addImages(response);
                        notifyItemRangeInserted(position, response.size());
                        setBeingUpdatedState(false);
                        swipeRefreshLayout.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setBeingUpdatedState(false);
                swipeRefreshLayout.setEnabled(true);
                pageNumber--;
                Toast.makeText(mContext, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
        setBeingUpdatedState(true);
        ApplicationController.getInstance().addToRequestQueue(request);

    }

}

