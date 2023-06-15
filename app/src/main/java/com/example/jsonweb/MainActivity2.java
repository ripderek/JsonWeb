package com.example.jsonweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity2 extends AppCompatActivity implements Asynchtask {
    private TextView txtList = (TextView)findViewById(R.id.txtList) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity2.this, MainActivity2.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        txtList.setText(result);
    }
}