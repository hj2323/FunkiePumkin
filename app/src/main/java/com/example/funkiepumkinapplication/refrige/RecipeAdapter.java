package com.example.funkiepumkinapplication.refrige;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>
        implements OnRecipeItemClickListener{
    ArrayList<Recipe> items = new ArrayList<Recipe>();
    OnRecipeItemClickListener listener;
    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recipe_item,parent,false);
        return new RecipeAdapter.ViewHolder(itemView, this);
    }

    public void setOnItemClickListener(OnRecipeItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        Recipe item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Recipe item){ //끝에 붙임
        items.add(item);
    }

    public void setItems(ArrayList<Recipe> items){ //한꺼번에 바꿀때
        this.items = items;
    }

    public void setItem(int position, Recipe item){ //내가 원하는 위치에 붙임
        items.set(position,item);
    }

    public Recipe getItem(int position){
        return items.get(position);
    }

    @Override
    public void onItemClick(RecipeAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivRefrigeRecipe;
        TextView tvRefrigeRecipe;

        public ViewHolder(@NonNull View itemView, final OnRecipeItemClickListener listener) {
            super(itemView);

            ivRefrigeRecipe = itemView.findViewById(R.id.ivRefrigeRecipe);
            tvRefrigeRecipe = itemView.findViewById(R.id.tvRefrigeRecipe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(RecipeAdapter.ViewHolder.this, view, position);
                    }
                }
            });

        }
        public void setItem(Recipe item){
            String url = "http://175.215.100.167:8899/resources/recipe_img/";

            ImageLoadTask task = new ImageLoadTask(url+item.getRecipeImg(),ivRefrigeRecipe);
            task.execute();
            tvRefrigeRecipe.setText(item.getRecipeName());
        }
    }
}
