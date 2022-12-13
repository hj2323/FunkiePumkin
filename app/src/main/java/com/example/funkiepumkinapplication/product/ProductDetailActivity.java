package com.example.funkiepumkinapplication.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView ivDetail;
    TextView tvDetailName, tvDetailPrice, tvDetailSeller, tvDetailStock, tvDetailDes;
    Integer productId;
    Button btn;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ivDetail = findViewById(R.id.ivDetail);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailSeller =findViewById(R.id.tvDetailSeller);
        tvDetailStock = findViewById(R.id.tvDetailStock);
        tvDetailDes = findViewById(R.id.tvDetailDes);

        Intent intent = getIntent();
        productId = intent.getIntExtra("productId",0);

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        productDetailRequest();

        btn = findViewById(R.id.btnCart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCartRequest(); //장바구니 이미 담긴거 토스트 //나중에 시간되면 수정
            }
        });

    }

    public void addCartRequest(){
        String url="http://175.215.100.167:8899/product/addCart";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"장바구니에 담았습니다",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"장바구니에 있는 상품입니다",Toast.LENGTH_LONG).show();
                    }
                }
        ){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("productId",productId+"");
                params.put("cartAmount","1");
                params.put("memberId","1"); //나중에 수정
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void productDetailRequest(){
        String url="http://175.215.100.167:8899/product/productDetailBody?productId=";
        StringRequest request = new StringRequest(Request.Method.GET, url+productId,
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
        Product product = gson.fromJson(response, Product.class);

        tvDetailName.setText(product.getProductName());
        tvDetailPrice.setText(product.getProductPrice()+"원");
        tvDetailSeller.setText("판매자: "+product.getMemberId()); //나중에 이름으로 수정
        tvDetailStock.setText("재고: "+product.getStock());
        tvDetailDes.setText(product.getProductDes());

        String url = "http://175.215.100.167:8899/resources/product_img/";

        ImageLoadTask task = new ImageLoadTask(url+product.getProductImg(),ivDetail);
        task.execute();

    }

}