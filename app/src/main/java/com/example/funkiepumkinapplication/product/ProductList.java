package com.example.funkiepumkinapplication.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.cart.Cart;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProductList extends AppCompatActivity {
    ViewPager pager;
    ImageView newiv1, newiv2, newiv3, newiv4, newiv5;
    TextView newtv1, newtv2, newtv3, newtv4, newtv5;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Product");
//        actionBar.setDisplayHomeAsUpEnabled(true);

        newiv1 = findViewById(R.id.newiv1);
        newiv2 = findViewById(R.id.newiv2);
        newiv3 = findViewById(R.id.newiv3);
        newiv4 = findViewById(R.id.newiv4);
        newiv5 = findViewById(R.id.newiv5);
        newtv1 = findViewById(R.id.newtv1);
        newtv2 = findViewById(R.id.newtv2);
        newtv3 = findViewById(R.id.newtv3);
        newtv4 = findViewById(R.id.newtv4);
        newtv5 = findViewById(R.id.newtv5);

        //추천
        pager = findViewById(R.id.bestpager);
        BestProductAdapter adapter = new BestProductAdapter(getLayoutInflater());
        pager.setAdapter(adapter);

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        newProductListRequest();


    }
    public void newProductListRequest(){
        String url="http://175.215.100.167:8899/product/newProductListBody";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
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

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> imgs = new ArrayList<>();
        ArrayList<Integer> productIds = new ArrayList<>();

        for(int i=0; i<products.length; i++){
            names.add(products[i].getProductName());
            imgs.add(products[i].getProductImg());
            productIds.add(products[i].getProductId());
        }

        newtv1.setText(names.get(0));
        newtv2.setText(names.get(1));
        newtv3.setText(names.get(2));
        newtv4.setText(names.get(3));
        newtv5.setText(names.get(4));

        String url = "http://175.215.100.167:8899/resources/product_img/";

        ImageLoadTask task1 = new ImageLoadTask(url+imgs.get(0),newiv1);
        ImageLoadTask task2 = new ImageLoadTask(url+imgs.get(1),newiv2);
        ImageLoadTask task3 = new ImageLoadTask(url+imgs.get(2),newiv3);
        ImageLoadTask task4 = new ImageLoadTask(url+imgs.get(3),newiv4);
        ImageLoadTask task5 = new ImageLoadTask(url+imgs.get(4),newiv5);
        task1.execute();
        task2.execute();
        task3.execute();
        task4.execute();
        task5.execute();

        newiv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productId",productIds.get(0));
                startActivity(intent);
            }
        });
        newiv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productId",productIds.get(1));
                startActivity(intent);
            }
        });
        newiv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productId",productIds.get(2));
                startActivity(intent);
            }
        });
        newiv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productId",productIds.get(3));
                startActivity(intent);
            }
        });
        newiv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productId",productIds.get(4));
                startActivity(intent);
            }
        });


    }
}