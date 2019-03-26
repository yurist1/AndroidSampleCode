package com.example.yrchoi.yurist.LoadMoreListener;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yrchoi.yurist.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<HashMap<String,String>> mDataset;
    private Context mcontext;
    private Activity mactivity;
    private OnItemClickListener listener;

    // for load more
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public interface OnItemClickListener {
        void onItemClick(HashMap<String, String> item);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void add(int position, HashMap<String,String> item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove( HashMap<String,String> item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PostRecyclerAdapter(Context context, ArrayList<HashMap<String, String>> myDataset, RecyclerView recyclerView) {

        mcontext = context;
        mactivity = (Activity)context;
        mDataset = myDataset;

        // load more
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mactivity).inflate(R.layout.list_item_post, parent, false);
            return new ViewHolderRow(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mactivity).inflate(R.layout.list_item_loading, parent, false);
            return new ViewHolderLoading(view);
        }
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (holder instanceof ViewHolderRow) {
            HashMap<String,String> map = mDataset.get(position);

            ViewHolderRow userViewHolder = (ViewHolderRow) holder;

            userViewHolder.txtEmail.setText(map.get("KEY_EMAIL"));
            userViewHolder.txtPhone.setText(map.get("KEY_PHONE"));
            //userViewHolder.email.setText(contact.getPhone());

            // binding item click listner
            userViewHolder.bind(mDataset.get(position), listener);
        } else if (holder instanceof ViewHolderLoading) {
            ViewHolderLoading loadingViewHolder = (ViewHolderLoading) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setOnItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    private class ViewHolderLoading extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ViewHolderLoading(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolderRow extends RecyclerView.ViewHolder {
        public TextView txtEmail, txtPhone;

        public ViewHolderRow(View v) {
            super(v);
            txtEmail = (TextView)v.findViewById(R.id.textViewTitle);
            txtPhone = (TextView)v.findViewById(R.id.textViewDescription);
        }

        public void bind(final HashMap<String,String> item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
