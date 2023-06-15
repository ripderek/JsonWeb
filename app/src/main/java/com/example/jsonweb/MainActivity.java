package com.example.jsonweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    //private TextView txtS = (TextView)findViewById(R.id.txtToken);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        /*
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
         */

    }
    @Override
    public void processFinish(String result) throws
            JSONException {
        //txtSaludo.setText("Resp: " + result );
        TextView txtS = (TextView)findViewById(R.id.txtToken);
         String tok = "";

        Log.i("Resp: ", result);
        JSONObject resp = new JSONObject(result);
        if(resp.has("error")){
            Toast.makeText(this, "Error Login" + resp.getString("error"), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Hola"+ resp.getString("access_token"), Toast.LENGTH_SHORT).show();
            txtS.setText("Token: " + resp.getString("access_token"));
            tok=  resp.getString("access_token");
            //ENviar el token a la otra actividad para enlistar los productos

            Intent i = new Intent(this, MainActivity2.class);
            i.putExtra("Token",tok);
            startActivity(i);

        }




        //Parsear un JSON
        /*
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = banco.getString("access_token").toString();
        }
        txtS.setText("Token: " + lstBancos);

         */



    }
    public void Enviar(View view) {
        //Se crea una instancia de la actividad que se quiere abrir
        //Intent i = new Intent(this, MainActivity2.class);
        //se envia el dato a la siguiente actividad
        //i.putExtra("cedula",txtCedula.getText().toString());
        //se inicia la actividad
        //

        EditText txtUser = (EditText)findViewById(R.id.txtCedula);
        EditText txtContra = (EditText)findViewById(R.id.txtNombres);


        //Recibir el token y verlo en el txt
        Map<String, String> datos = new HashMap<String, String>();
        datos.put("correo", txtUser.getText().toString());
        datos.put("clave", txtContra.getText().toString());
        WebService ws = new WebService(
                "https://api.uealecpeterson.net/public/login",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("POST");

    }
}