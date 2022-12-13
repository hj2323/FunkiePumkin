package com.example.funkiepumkinapplication.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.funkiepumkinapplication.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VegetableList extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ProductAdapter adapter;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_list);

        recyclerView = findViewById(R.id.vegetableRecyclerView);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter();

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        productListRequest(); //값넣기


        adapter.setOnItemClickListener(new OnProductItemClickListener() {
            @Override
            public void onItemClick(ProductAdapter.ViewHolder holder, View view, int position) {
                Product product = adapter.getItem(position);
                //Toast.makeText(getApplicationContext(),product.getProductId()+"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productId",product.getProductId());
                startActivity(intent);
            }
        });

    }
    public void productListRequest(){
        String url="http://175.215.100.167:8899/product/VegetableListBody";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) { //정상적인 처리
                        //Log.i("결과", response);
                        processResponse(response);
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
    }

    private void processResponse(String response){
        Gson gson = new Gson();
        Product[] products = gson.fromJson(response, Product[].class);

        for(int i=0; i<products.length; i++){
            Log.i("확인",products[i].getProductPrice()+"");
            adapter.addItem(new Product(products[i].getProductId(),products[i].getProductName(),products[i].getProductPrice(),products[i].getProductImg()));
            recyclerView.setAdapter(adapter);
        }
    }


}