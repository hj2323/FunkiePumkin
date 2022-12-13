package com.example.funkiepumkinapplication.cart;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
        implements OnCartItemClickListener{ //2.extends  //1.의 내용을 쓰겠다 //4.implements
    ArrayList<Cart> items = new ArrayList<Cart>();
    OnCartItemClickListener listener; //이벤트1.
    static RequestQueue requestQueue;//*

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //3. 세개를 override
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(itemView, this); //이벤트2. 이벤트listener도 같이 리턴
    }

    public void setOnItemClickListener(OnCartItemClickListener listener){ //이벤트3.
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) { //이벤트5.
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{ //1. //화면 왔다갔다 할 때 뷰홀더 만들면 새로 만들지 않고 기존에 있던 것을 가져옴
        TextView tvCartProductName,tvCartProductPrice,tvCartProductAmount;
        ImageView ivCartImg;
        Button btnMinus,btnPlus,btnAmountChange,btnCartDelete;

        public ViewHolder(@NonNull View itemView, final OnCartItemClickListener listener) {
            super(itemView);

            tvCartProductName = itemView.findViewById(R.id.tvCartProductName);
            tvCartProductPrice = itemView.findViewById(R.id.tvCartProductPrice);
            tvCartProductAmount = itemView.findViewById(R.id.tvCartProductAmount);
            ivCartImg = itemView.findViewById(R.id.ivCartImg);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnAmountChange = itemView.findViewById(R.id.btnAmountChange);
            btnCartDelete = itemView.findViewById(R.id.btnCartDelete);

            itemView.setOnClickListener(new View.OnClickListener() { //이벤트4.
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }
        public void setItem(Cart item){
            tvCartProductName.setText(item.getProductName());
            tvCartProductPrice.setText(item.getProductPrice()+"원");
            tvCartProductAmount.setText(item.getCartAmount()+"");

            if(requestQueue==null){ //*
                requestQueue = Volley.newRequestQueue(itemView.getContext());
            }

            int cartId = item.getCartId();

            String url = "http://175.215.100.167:8899/resources/product_img/";

            ImageLoadTask task = new ImageLoadTask(url+item.getProductImg(),ivCartImg);
            task.execute();

            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int amount = Integer.parseInt(tvCartProductAmount.getText().toString());
                    amount++;
                    tvCartProductAmount.setText(amount+"");
                }
            });
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int amount = Integer.parseInt(tvCartProductAmount.getText().toString());
                    if(amount>1){
                        amount--;
                        tvCartProductAmount.setText(amount+"");
                    }
                }
            });

            btnAmountChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url="http://175.215.100.167:8899/product/cartChange";
                    url = url + "?cartId=" + cartId + "&cartAmount=" +tvCartProductAmount.getText().toString();
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    Log.i("결과", response);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("결과", error.getMessage());
                                }
                            }
                    ){
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            return params;
                        }
                    };
                    request.setShouldCache(false);
                    requestQueue.add(request);
                    //화면새로고침넣기
                }
            });
            btnCartDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url="http://175.215.100.167:8899/product/cartDelete";
                    url = url + "?cartId=" + cartId;
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    Log.i("결과", response);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("결과", error.getMessage());
                                }
                            }
                    ){
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            return params;
                        }
                    };
                    request.setShouldCache(false);
                    requestQueue.add(request);
                    //화면새로고침넣기
                    //((CartActivity)CartActivity.mContext).refresh();
                }
            });


        }

    }


}