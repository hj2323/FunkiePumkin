package com.example.funkiepumkinapplication.product;

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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
implements OnProductItemClickListener { //2.extends  //1.의 내용을 쓰겠다 //4.implements
    ArrayList<Product> items = new ArrayList<Product>();
    OnProductItemClickListener listener; //이벤트1.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //3. 세개를 override
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.product_item,parent,false);
        return new ViewHolder(itemView, this); //이벤트2. 이벤트listener도 같이 리턴
    }

    public void setOnItemClickListener(OnProductItemClickListener listener){ //이벤트3.
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Product item){ //끝에 붙임
        items.add(item);
    }

    public void setItems(ArrayList<Product> items){ //한꺼번에 바꿀때
       this.items = items;
    }

    public void setItem(int position, Product item){ //내가 원하는 위치에 붙임
        items.set(position,item);
    }

    public Product getItem(int position){
        return items.get(position);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) { //이벤트5.
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{ //1. //화면 왔다갔다 할 때 뷰홀더 만들면 새로 만들지 않고 기존에 있던 것을 가져옴
        TextView tv1,tv2;
        ImageView iv;

        public ViewHolder(@NonNull View itemView, final OnProductItemClickListener listener) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.fruitName);
            tv2 = itemView.findViewById(R.id.fruitPrice);
            iv = itemView.findViewById(R.id.fruitIv);

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
        public void setItem(Product item) {
            tv1.setText(item.getProductName());
            tv2.setText(item.getProductPrice()+"원");

            String url = "http://175.215.100.167:8899/resources/product_img/";

            ImageLoadTask task = new ImageLoadTask(url+item.getProductImg(),iv);
            task.execute();

        }


    }
}
