package com.example.funkiepumkinapplication.refrige;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.R;

import java.util.ArrayList;

public class RefrigeAdapter extends RecyclerView.Adapter<RefrigeAdapter.ViewHolder>
        implements OnRefrigeItemClickListener{
    ArrayList<Refrige> items = new ArrayList<Refrige>();
    OnRefrigeItemClickListener listener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.refrige_item,parent,false);
        return new ViewHolder(itemView, this);
    }

    public void setOnItemClickListener(OnRefrigeItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Refrige item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Refrige item){ //끝에 붙임
        items.add(item);
    }

    public void setItems(ArrayList<Refrige> items){ //한꺼번에 바꿀때
        this.items = items;
    }

    public void setItem(int position, Refrige item){ //내가 원하는 위치에 붙임
        items.set(position,item);
    }

    public Refrige getItem(int position){
        return items.get(position);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivRefrige;

        public ViewHolder(@NonNull View itemView, final OnRefrigeItemClickListener listener) {
            super(itemView);

            ivRefrige = itemView.findViewById(R.id.ivRefrige);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }
        public void setItem(Refrige item){
            String url = "http://175.215.100.167:8899/resources/product_img/";

            ImageLoadTask task = new ImageLoadTask(url+item.getProductImg(),ivRefrige);
            task.execute();
        }
    }
}

