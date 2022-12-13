package com.example.funkiepumkinapplication.member;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText editid, editpass;
    static RequestQueue requestQueue;
    private String TAG = "JoinActivity";

    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);


        editid=findViewById(R.id.editid);
        editpass=findViewById(R.id.editpass);
        TextView btnJoin = (TextView)findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joinIntent = new Intent(LoginActivity.this, JoinActivity.class);
                LoginActivity.this.startActivity(joinIntent);
            }
        });


        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
    }
    public void mOnClicked(View view){
        switch(view.getId()){
            case R.id.btnLogin:
                LoginRequest();
                break;


        }
    }

    public void LoginRequest(){
        String url="http://192.168.75.44:8899/member/androidLogin";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "응답->"+response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "에러->"+error.getMessage());
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<String, String>();
                params.put("userId", editid.getText().toString());
                params.put("userPassword", editpass.getText().toString());
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void processResponse(String response){

        Gson gson=new Gson();
        Member member=gson.fromJson(response, Member.class);
        if(member!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            dialog = builder.setMessage(member.getUserName()+"님이 로그인 했습니다.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
            Intent memberIntent = new Intent(LoginActivity.this, MainActivity.class);
            memberIntent.putExtra("memberId",member.getMemberId());
            LoginActivity.this.startActivity(memberIntent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            dialog = builder.setMessage("아이디, 비밀번호를 다시 확인하세요")
                    .setNegativeButton("다시 시도", null)
                    .create();
            dialog.show();
        }
//        for(int i=0; i<boards.length;i++){
//            println(boards[i].toString());
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }
}