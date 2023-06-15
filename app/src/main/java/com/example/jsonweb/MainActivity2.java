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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        String token = getIntent().getStringExtra("Token");
        //lbllista.setText(token);




        Map<String, String> datos = new HashMap<String, String>();
        datos.put("fuente","1");
        datos.put("Authorization","Bearer"+token);
        WebService ws= new
                WebService("https://api.uealecpeterson.net/public/productos/search",
                datos, MainActivity2.this, MainActivity2.this);
        ws.execute("POST","access_token",token);


    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView lbllista = (TextView)findViewById(R.id.txtList);
        lbllista.setText(result);
    }
}