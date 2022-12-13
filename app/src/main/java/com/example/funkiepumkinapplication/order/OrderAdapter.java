package com.example.funkiepumkinapplication.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.cart.Cart;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    ArrayList<Cart> items = new ArrayList<Cart>();
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //3. 세개를 override
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.order_cart_item,parent,false);
        return new OrderAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Cart item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Cart item){ //끝에 붙임
        items.add(item);
    }

    public void setItems(ArrayList<Cart> items){ //한꺼번에 바꿀때
        this.items = items;
    }

    public void setItem(int position, Cart item){ //내가 원하는 위치에 붙임
        items.set(position,item);
    }

    public Cart getItem(int position){
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{ //1. //화면 왔다갔다 할 때 뷰홀더 만들면 새로 만들지 않고 기존에 있던 것을 가져옴
        TextView tv1,tv2,tv3;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tvOrderCartProductName);
            tv2 = itemView.findViewById(R.id.tvOrderCartProductPrice);
            tv3 = itemView.findViewById(R.id.tvOrderCartProductAmount);
            iv = itemView.findViewById(R.id.ivOrderCartImg);


        }
        public void setItem(Cart item) {
            tv1.setText(item.getProductName());
            tv2.setText(item.getProductPrice()+"원");
            tv3.setText(item.getCartAmount()+"개");

            String url = "http://175.215.100.167:8899/resources/product_img/";

            ImageLoadTask task = new ImageLoadTask(url+item.getProductImg(),iv);
            task.execute();

        }


    }
}

