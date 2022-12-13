package com.example.funkiepumkinapplication.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.order.OrderActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CartAdapter adapter;
    static RequestQueue requestQueue;
    RadioGroup radioGroup;
    TextView tvProductsPrice, tvShipping, tvPayment;
    Button btnOrderForm;
    int radio;
    public static Context mContext; //*

    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //로그인유지
        Intent memberIntent = getIntent();
        memberId = memberIntent.getIntExtra("memberId",0);

        fab=findViewById(R.id.fab);
        if(memberId>0) {//로그인 상태일때, memberId를 인텐트로 받아왔을때
            fab.setVisibility(View.GONE);
        }else{//로그아웃 상태일때, memberId를 인텐트로 받아왔을때

            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        //하단바
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_stamp:
                        Intent stampIntent = new Intent(getApplicationContext(), StampActivity.class);
                        stampIntent.putExtra("memberId",memberId);
                        startActivity(stampIntent);
                        finish();
                        break;
                    case R.id.action_home:
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        mainIntent.putExtra("memberId",memberId);
                        startActivity(mainIntent);
                        finish();
                        break;
                    case R.id.action_refridge:
                        Intent refrigeIntent = new Intent(getApplicationContext(), RefrigeActivity.class);
                        refrigeIntent.putExtra("memberId",memberId);
                        startActivity(refrigeIntent);
                        finish();
                        break;
                    case R.id.action_event:
                        Intent eventIntent = new Intent(getApplicationContext(), EventActivity.class);
                        eventIntent.putExtra("memberId",memberId);
                        startActivity(eventIntent);
                        finish();
                        break;
                }
            }
        });


        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("Cart");  //액션바 제목설정
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.cartRecyclerView);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter();
        mContext = this; //*

        radioGroup = findViewById(R.id.radioGroup);
        tvProductsPrice = findViewById(R.id.tvProductsPrice);
        tvShipping = findViewById(R.id.tvShipping);
        tvPayment = findViewById(R.id.tvPayment);

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        cartListRequest();

        adapter.setOnItemClickListener(new OnCartItemClickListener() { //필요없으면 지우기
            @Override
            public void onItemClick(CartAdapter.ViewHolder holder, View view, int position) {
                //Cart cart = adapter.getItem(position);
                //Toast.makeText(getApplicationContext(), person.getName()+","+person.getMobile(),Toast.LENGTH_SHORT).show();
            }
        });

        radio = 0;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btnPickup :
                        radio = 0;
                        tvShipping.setText("0");
                        tvPayment.setText((Integer.parseInt(tvProductsPrice.getText().toString())+
                                Integer.parseInt(tvShipping.getText().toString()))+"");
                        break;
                    case R.id.btnDelivery :
                        radio = 1;
                        tvShipping.setText("2000");
                        tvPayment.setText((Integer.parseInt(tvProductsPrice.getText().toString())+
                                Integer.parseInt(tvShipping.getText().toString()))+"");
                        break;
                }
            }
        });

        btnOrderForm = findViewById(R.id.btnOrderForm);
        btnOrderForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), OrderActivity.class);
                intent.putExtra("tag",radio);
                startActivity(intent);
            }
        });

    }
    public void cartListRequest(){
        String url="http://175.215.100.167:8899/product/cartList?memberId=1"; //수정하기
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) { //정상적인 처리
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
        Cart[] carts = gson.fromJson(response, Cart[].class);

        int productsPrice=0;

        for(int i=0; i<carts.length; i++){
            adapter.addItem(new Cart(carts[i].getCartId(),carts[i].getProductId(),carts[i].getCartAmount(),carts[i].getMemberId(),
                    carts[i].getProductName(),carts[i].getProductPrice(),carts[i].getProductImg()));
            recyclerView.setAdapter(adapter);

            productsPrice = productsPrice + carts[i].getProductPrice() * carts[i].getCartAmount();
            tvProductsPrice.setText(productsPrice+"");
            tvPayment.setText(productsPrice+"");
        }
    }

    public void refresh(){ //*
        adapter.notifyDataSetChanged();
    }
}