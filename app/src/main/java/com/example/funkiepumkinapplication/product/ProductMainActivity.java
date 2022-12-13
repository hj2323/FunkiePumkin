package com.example.funkiepumkinapplication.product;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProductMainActivity extends TabActivity {
    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

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

        //상단메뉴
        TabHost tabHost = getTabHost();

        Intent intent1 = new Intent().setClass(this, ProductList.class);
        TabHost.TabSpec tabSpecBest = tabHost.newTabSpec("Best").setIndicator("추천");
        tabSpecBest.setContent(intent1);
        tabHost.addTab(tabSpecBest);

        Intent intent2 = new Intent().setClass(this, FruitList.class);
        TabHost.TabSpec tabSpecFruit = tabHost.newTabSpec("Fruit").setIndicator("과일");
        tabSpecFruit.setContent(intent2);
        tabHost.addTab(tabSpecFruit);

        Intent intent3 = new Intent().setClass(this, VegetableList.class);
        TabHost.TabSpec tabSpecVegetable = tabHost.newTabSpec("Vegetable").setIndicator("채소");
        tabSpecVegetable.setContent(intent3);
        tabHost.addTab(tabSpecVegetable);

        TabHost.TabSpec tabSpecHerb = tabHost.newTabSpec("Herb").setIndicator("허브");
        tabSpecHerb.setContent(R.id.tabHerb);
        tabHost.addTab(tabSpecHerb);

        TabHost.TabSpec tabSpecMilk = tabHost.newTabSpec("Milk").setIndicator("식물성 우유");
        tabSpecMilk.setContent(R.id.tabMilk);
        tabHost.addTab(tabSpecMilk);

        TabHost.TabSpec tabSpecSpices = tabHost.newTabSpec("Spices").setIndicator("향신료");
        tabSpecSpices.setContent(R.id.tabSpices);
        tabHost.addTab(tabSpecSpices);

        tabHost.setCurrentTab(0);
    }
}