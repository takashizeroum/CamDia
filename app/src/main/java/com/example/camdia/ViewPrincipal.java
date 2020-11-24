package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewPrincipal extends AppCompatActivity {

    AdaptadorP Adapter ;
    private ArrayList<ModelUser> listar = new ArrayList<>();
    GridView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_d_l);

        refrescaVisualMural();
        findViewById(R.id.muralpaginabtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refrescaVisualMural();


            }
        });


       /* findViewById(R.id.btnformmural).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.formmural).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.btnmuralformcancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.formmural).setVisibility(View.GONE);
            }
        });
*/

        findViewById(R.id.sairbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), ViewLogin.class));
                
            }
        });

        findViewById(R.id.simuladorbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(ViewPrincipal.this, MapsActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fade_in,R.anim.move_direita);
                ActivityCompat.startActivity(ViewPrincipal.this,go,activityOptionsCompat.toBundle());

            }
        });
        findViewById(R.id.perfilbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(ViewPrincipal.this, ViewUser.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fade_in,R.anim.move_direita);
                ActivityCompat.startActivity(ViewPrincipal.this,go,activityOptionsCompat.toBundle());

            }
        });

    }
    private void refrescaVisualMural(){

        DBLocalController controller = new DBLocalController(getApplicationContext());
        ModelUser us = controller.resgata();

        HashMap<String, String> params = new HashMap<>();
        params.put("login", "login");
        params.put("senha", "senha");
        params.put("empresa", String.valueOf(us.getEmpresa()));

        ViewPrincipal.PNRViewP request = new ViewPrincipal.PNRViewP(API.URL_mural, params,API.CODE_POST_REQUEST);
        request.execute();


    }

    private void visualMural(JSONArray array) throws JSONException {


        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            listar.add(new ModelUser(obj.getString("nome"),obj.getString("data"), obj.getString("msg"),
                    obj.getString("extras"), 0, "não utilizado", 0,
                    0.0, 0, 0.0,obj.getString("empresa")));

        }
        Adapter = new AdaptadorP(ViewPrincipal.this, R.layout.listitemformural, listar);
        listView = findViewById(R.id.tdlmural);

        listView.setAdapter(Adapter);
        Toast.makeText(getApplicationContext(), "Atualizado com Sucesso", Toast.LENGTH_SHORT).show();


    }


    public class PNRViewP extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;


        public PNRViewP(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                final JSONObject jsonObject = new JSONObject(s);
                visualMural(jsonObject.getJSONArray("users"));



            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Nenhuma postagem encontrada ", Toast.LENGTH_SHORT).show();

            }


        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == API.CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == API.CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }


    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_esquerda,R.anim.fade_out);
    }
}