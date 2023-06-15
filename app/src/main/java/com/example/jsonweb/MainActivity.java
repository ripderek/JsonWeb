package com.example.jsonweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    private TextView txtS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtS = (TextView)findViewById(R.id.txtHolla);
    //PIRMER LIBRERIA (AUTH: ING. CRISTIAN )
    /*
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr="
                        + "cristian" + "&pass=" + "cristian123",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
        */

    //consumir api con librebria de google grdadle scripts
    /*
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtS.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtS.setText("That didn't work!");
                    }
                });
        queue.add(stringRequest);
    */

        // Consumir API de KUSKHI COn LIBRERIA DEL ING XD



        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");

    }
    @Override
    public void processFinish(String result) throws
            JSONException {
        //txtSaludo.setText("Resp: " + result );
         //txtS = (TextView)findViewById(R.id.txtHolla);
        //txtS.setText(result);


        //Parsear un JSON
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("name").toString();
        }
        txtS.setText("Respuesta WS Lista de Bancos" + lstBancos);
    }
    public void Enviar(View view){
        //Se crea una instancia de la actividad que se quiere abrir
        Intent i = new Intent(this, MainActivity2.class);
        //se envia el dato a la siguiente actividad
        //i.putExtra("cedula",txtCedula.getText().toString());
        //se inicia la actividad
        startActivity(i);
    }
}