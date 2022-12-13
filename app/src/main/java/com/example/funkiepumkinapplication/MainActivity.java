package com.example.funkiepumkinapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.funkiepumkinapplication.Map.MapActivity;
import com.example.funkiepumkinapplication.cart.CartActivity;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.member.Member;
import com.example.funkiepumkinapplication.member.MemberActivity;
import com.example.funkiepumkinapplication.product.ProductMainActivity;
import com.example.funkiepumkinapplication.refrige.RecipeActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    //서랍메뉴
    //private AppBarConfiguration mAppBarConfiguration;

    //하단바
    private BottomNavigationView bottomNavigationView;
//    private FragmentManager fm;
//    private FragmentTransaction ft;
//
//    private StampActivity frag1;
//    private MainFragment frag2;
//    private RefrigeActivity frag3;
//    //private EventActivity frag4;

    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //로그인유지
        Intent memberIntent = getIntent();
        memberId = memberIntent.getIntExtra("memberId",0);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
////서랍메뉴 관련코드 주석처리
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_refrige, R.id.nav_stamp, R.id.nav_cart, R.id.nav_event)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//        //위까지 기존코드


        fab=findViewById(R.id.fab);
        if(memberId>0) {//로그인 상태일때, memberId를 인텐트로 받아왔을때
            fab.setVisibility(View.GONE);
        }else{//로그아웃 상태일때, memberId를 인텐트로 받아왔을때

            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(intent);
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
//        frag1 = new StampActivity();
//        frag2 = new MainFragment();
//        frag3 = new RefrigeActivity();
//        //frag4 = new EventActivity();
////        setFrag(1); //첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택


    }

    public void mOnClicked(View view){
        switch(view.getId()){
            case R.id.ivStamp:
                Intent stampIntent = new Intent(MainActivity.this, StampActivity.class);
                stampIntent.putExtra("memberId",memberId);
                MainActivity.this.startActivity(stampIntent);
                break;
            case R.id.ivProduct:
                Intent productIntent = new Intent(MainActivity.this, ProductMainActivity.class);
                productIntent.putExtra("memberId",memberId);
                MainActivity.this.startActivity(productIntent);
                break;
            case R.id.ivCart:
                Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                cartIntent.putExtra("memberId",memberId);
                MainActivity.this.startActivity(cartIntent);
                break;
            case R.id.ivMember:
                Intent memberIntent = new Intent(MainActivity.this, MemberActivity.class);
                memberIntent.putExtra("memberId",memberId);
                MainActivity.this.startActivity(memberIntent);
                break;
            case R.id.ivRefrige:
                Intent refrigeIntent = new Intent(MainActivity.this, RefrigeActivity.class);
                refrigeIntent.putExtra("memberId",memberId);
                MainActivity.this.startActivity(refrigeIntent);
                break;
            case R.id.ivEvent:
                Intent eventIntent = new Intent(MainActivity.this, EventActivity.class);
                eventIntent.putExtra("memberId",memberId);
                MainActivity.this.startActivity(eventIntent);
                break;
            case R.id.ivMap:
                Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(mapIntent);
                break;

        }
    }

//    //프래그먼트 교체가 일어나는 실행문
//    private void setFrag(int n){
//        fm=getSupportFragmentManager();
//        ft=fm.beginTransaction();
//        switch(n){
////            case 0:
////                ft.replace(R.id.main_frame, frag1);
////                ft.commit();
////                break;
////            case 1:
////                ft.replace(R.id.main_frame, frag2);
////                ft.commit();
////                break;
////            case 2:
////                ft.replace(R.id.main_frame, frag3);
////                ft.commit();
////                break;
////            case 3:
////                ft.replace(R.id.main_frame, frag4);
////                ft.commit();
////                break;
//
//        }
//
//
//    }


//    //서랍메뉴 관련 코드 주석처리
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//    //위까지 기존코드





}