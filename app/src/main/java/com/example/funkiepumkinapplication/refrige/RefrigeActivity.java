package com.example.funkiepumkinapplication.refrige;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RefrigeActivity extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView2;
    GridLayoutManager gridLayoutManager;
    RefrigeAdapter adapter;
    static RequestQueue requestQueue;
    View dialogView;
    TextView tvRefrigeQty, tvRefrigeExp, tvRefrigeExpMessage;
    Button btnRefrigeMinus, btnRefrigePlus, btnRefrigeQtyChange, btnRefrigeDelete;
    LinearLayoutManager layoutManager;
    RecipeAdapter adapter2;


    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrige);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Refrige");
        actionBar.setDisplayHomeAsUpEnabled(true);

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


        recyclerView = findViewById(R.id.refrigeRecyclerView);
        gridLayoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RefrigeAdapter();

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        RefrigeListRequest();

        adapter.setOnItemClickListener(new OnRefrigeItemClickListener() {
            @Override
            public void onItemClick(RefrigeAdapter.ViewHolder holder, View view, int position) {
                Refrige refrige = adapter.getItem(position);
                //Toast.makeText(getApplicationContext(), "확인",Toast.LENGTH_SHORT).show();
                //dialog 띄우기
                dialogView = View.inflate(RefrigeActivity.this, R.layout.refrige_dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(RefrigeActivity.this);

                tvRefrigeQty = dialogView.findViewById(R.id.tvRefrigeQty);
                tvRefrigeExp = dialogView.findViewById(R.id.tvRefrigeExp);
                tvRefrigeExpMessage = dialogView.findViewById(R.id.tvRefrigeExpMessage);
                tvRefrigeQty.setText(refrige.getProductAmount()+"");
                tvRefrigeExp.setText(refrige.getExp());


//                SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
//                String today = format1.format(System.currentTimeMillis());
                try {
                    SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
                    Date now = new Date();
                    now.setHours(now.getHours()+9);
                    String today = format1.format(now);
                    String exp = refrige.getExp();
                    String format = "yyMMdd";
                    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
                    Date firstDate = sdf.parse(today);
                    Date secondDate = sdf.parse(exp);
                    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    if(diff>0){
                        tvRefrigeExpMessage.setText("유통기한이 "+diff+"일 남았습니다");
                    }else if(diff==0){
                        tvRefrigeExpMessage.setText("유통기한이 오늘까지 입니다!");
                        String strColor = "#DC143C";
                        tvRefrigeExpMessage.setTextColor(Color.parseColor(strColor));
                    }else{
                        tvRefrigeExpMessage.setText("유통기한이 지났습니다");
                        String strColor = "#4682B4";
                        tvRefrigeExpMessage.setTextColor(Color.parseColor(strColor));
                    }
                } catch (Exception e) { e.printStackTrace(); }

                btnRefrigeMinus = dialogView.findViewById(R.id.btnRefrigeMinus);
                btnRefrigePlus = dialogView.findViewById(R.id.btnRefrigePlus);
                btnRefrigeQtyChange = dialogView.findViewById(R.id.btnRefrigeQtyChange);
                btnRefrigeDelete = dialogView.findViewById(R.id.btnRefrigeDelete);

                btnRefrigeMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int amount = Integer.parseInt(tvRefrigeQty.getText().toString());
                        if(amount>1){
                            amount--;
                            tvRefrigeQty.setText(amount+"");
                        }
                    }
                });
                btnRefrigePlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int amount = Integer.parseInt(tvRefrigeQty.getText().toString());
                        amount++;
                        tvRefrigeQty.setText(amount+"");
                    }
                });
                btnRefrigeQtyChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url="http://175.215.100.167:8899/product/refrigeChange";
                        url = url + "?refrigeId=" + refrige.getRefrigeId() + "&productAmount=" +tvRefrigeQty.getText().toString();
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
                        //adapter.notifyDataSetChanged();
                    }
                });
                btnRefrigeDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url="http://175.215.100.167:8899/product/refrigeDelete";
                        url = url + "?refrigeId=" + refrige.getRefrigeId();
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

                //추천레시피
                recyclerView2 = dialogView.findViewById(R.id.refrigeRecipeRecyclerView);
                layoutManager = new LinearLayoutManager(dialogView.getContext(),LinearLayoutManager.VERTICAL, false);
                recyclerView2.setLayoutManager(layoutManager);
                adapter2 = new RecipeAdapter();
                String productName= refrige.getProductName();
                recipeListRequest(productName);
                adapter2.setOnItemClickListener(new OnRecipeItemClickListener() {
                    @Override
                    public void onItemClick(RecipeAdapter.ViewHolder holder, View view, int position) {
                        Recipe recipe = adapter2.getItem(position);
                        Intent intent = new Intent(getApplication(), RecipeActivity.class);
                        intent.putExtra("recipeId",recipe.getRecipeId());
                        startActivity(intent);
                    }
                });


                dlg.setTitle(refrige.getProductName());
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });
    }
    public void RefrigeListRequest(){
        String url="http://175.215.100.167:8899/product/refrigeList?memberId=1"; //수정
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
        Refrige[] refriges = gson.fromJson(response, Refrige[].class);

        for(int i=0; i<refriges.length; i++){
            adapter.addItem(new Refrige(refriges[i].getRefrigeId(),refriges[i].getProductId(),refriges[i].getProductName(),
                    refriges[i].getProductAmount(),refriges[i].getExp(),refriges[i].getProductImg()));
            recyclerView.setAdapter(adapter);
        }
    }


    public void recipeListRequest(String productName){ 
        String url="http://175.215.100.167:8899/product/recipeListBody";
        url = url + "?productName=" + productName;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        processResponse2(response);
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

    private void processResponse2(String response){
        Gson gson = new Gson();
        Recipe[] recipes = gson.fromJson(response, Recipe[].class);

        for(int i=0; i<recipes.length; i++){
            adapter2.addItem(new Recipe(recipes[i].getRecipeId(),recipes[i].getRecipeName(),recipes[i].getRecipeIngredient(),
                    recipes[i].getRecipeCookingorder(),recipes[i].getRecipeImg()));
            recyclerView2.setAdapter(adapter2);
        }
    }
}

