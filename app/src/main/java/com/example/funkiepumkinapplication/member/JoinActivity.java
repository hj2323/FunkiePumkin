package com.example.funkiepumkinapplication.member;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.R;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    EditText editid, editpass, editname, editphone;
    static RequestQueue requestQueue;
    private String TAG = "JoinActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Join");
        actionBar.setDisplayHomeAsUpEnabled(true);

        editid=findViewById(R.id.editid);
        editpass=findViewById(R.id.editpass);
        editname=findViewById(R.id.editname);
        editphone=findViewById(R.id.editphone);

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
    }
    public void mOnClicked(View view){
        switch(view.getId()){
            case R.id.btnJoin:
                sendmakeRequest();
                Intent loginIntent = new Intent(JoinActivity.this, LoginActivity.class);
                JoinActivity.this.startActivity(loginIntent);
                break;

        }
    }


    public void sendmakeRequest(){
        String url="http://192.168.75.44:8899/member/androidRegister";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "응답->"+response);
                        //processResponse(response);
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
                params.put("userName", editname.getText().toString());
                params.put("userTel", editphone.getText().toString());
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

}