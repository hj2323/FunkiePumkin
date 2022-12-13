package com.example.funkiepumkinapplication.cart;

import android.view.View;

public interface OnCartItemClickListener {
    public void onItemClick(CartAdapter.ViewHolder holder, View view, int position);
}
