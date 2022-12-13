package com.example.funkiepumkinapplication.event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hitomi.cmlibrary.CircleMenu;

import com.example.funkiepumkinapplication.R;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class EventActivity extends AppCompatActivity {
    CircleMenu circleMenu;
    ConstraintLayout constraintLayout;

    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Event");
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

        circleMenu = (CircleMenu) findViewById(R.id.circle);
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraint_layout);

        circleMenu.setMainMenu(Color.parseColor("#cdcdcd"), R.drawable.ic_baseline_shutter_speed_24, R.drawable.ic_baseline_stars_24)
                .addSubMenu(Color.parseColor("#ffc75f"), R.drawable.ic_baseline_stars_24)
                .addSubMenu(Color.parseColor("#845ec2"), R.drawable.ic_baseline_stars_24)
                .addSubMenu(Color.parseColor("#f9f871"), R.drawable.ic_baseline_stars_24)
                .addSubMenu(Color.parseColor("#ff5e78"), R.drawable.ic_baseline_stars_24)
                .addSubMenu(Color.parseColor("#ffc1b6"), R.drawable.ic_baseline_stars_24)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        if(index>=0){
                            Intent eventResultIntent = new Intent(EventActivity.this, EventResultActivity.class);
                            EventActivity.this.startActivity(eventResultIntent);
                        }
                    }
                });


    }
}