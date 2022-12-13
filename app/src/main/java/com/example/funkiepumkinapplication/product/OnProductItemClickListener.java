package com.example.funkiepumkinapplication.product;

import android.view.View;

public interface OnProductItemClickListener { //이벤트 처리할 인터페이스
    public void onItemClick(ProductAdapter.ViewHolder holder, View view, int position);
}
