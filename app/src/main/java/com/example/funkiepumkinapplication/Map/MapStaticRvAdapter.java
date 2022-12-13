package com.example.funkiepumkinapplication.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funkiepumkinapplication.R;

import java.util.ArrayList;

public class MapStaticRvAdapter extends RecyclerView.Adapter<MapStaticRvAdapter.MapStaticRvViewHolder>{

    private ArrayList<MapStaticRvModel> items;
    //check if items selected or not
    int row_index;

    public MapStaticRvAdapter(ArrayList<MapStaticRvModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MapStaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_static_rv_item,parent, false);
        MapStaticRvViewHolder mapStaticRvViewHolder = new MapStaticRvViewHolder(view);
        return mapStaticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MapStaticRvViewHolder holder, int position) {
        MapStaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        holder.linearLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.map_rv_selected_bg);
        }else{
            holder.linearLayout.setBackgroundResource(R.drawable.map_rv_bg);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MapStaticRvViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public MapStaticRvViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
