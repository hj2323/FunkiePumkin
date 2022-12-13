package com.example.funkiepumkinapplication.Map;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funkiepumkinapplication.Map.DRvIntetface.LoadMore;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.product.Product;

import java.util.ArrayList;
import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;
    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder{
    public TextView name, address, tel;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        address = itemView.findViewById(R.id.address);
        tel = itemView.findViewById(R.id.tel);
    }
}

public class MapDynamicRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    LoadMore loadMore;
    boolean isLoading;
    Activity activity;
    ArrayList<MapDynamicRvModel> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public MapDynamicRvAdapter(RecyclerView recyclerView, Activity activity, ArrayList<MapDynamicRvModel> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                    if(loadMore!=null)
                        loadMore.onLoadMore();
                    isLoading=true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position)==null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(LoadMore loadMore){
        this.loadMore=loadMore;
    }

    //inflate our layouts
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType == VIEW_TYPE_ITEM){
           View view = LayoutInflater.from(activity).inflate(R.layout.map_dynamic_rv_item, parent, false);
           return new LoadingViewHolder(view);
       }else if(viewType==VIEW_TYPE_LOADING){
           View view = LayoutInflater.from(activity).inflate(R.layout.map_dynamic_rv_progress_bar, parent, false);
           return new LoadingViewHolder(view);
       }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            MapDynamicRvModel item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.name.setText(items.get(position).getShopName());
            viewHolder.address.setText(items.get(position).getShopAddress());
            viewHolder.tel.setText(items.get(position).getShopTel());

        }else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setLoaded(){
        isLoading = false;
    }
}
