package com.example.funkiepumkinapplication.refrige;

import android.view.View;

public interface OnRecipeItemClickListener {
    public void onItemClick(RecipeAdapter.ViewHolder holder, View view, int position);
}
