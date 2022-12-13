package com.example.funkiepumkinapplication.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funkiepumkinapplication.R;


public class OrderCompletedActivity extends AppCompatActivity {
    Button btnProductList,btnRefrige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed);

        btnProductList = findViewById(R.id.btnProductList);
        btnRefrige = findViewById(R.id.btnRefrige);

//        btnProductList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), ProductMainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnRefrige.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), RefrigeActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}