package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewHistorico extends AppCompatActivity {

    AdaptadorH Adapter ;
    private ArrayList<ModelUser> listar = new ArrayList<>();
    ListView listView;
    ModelUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        //adapta spinner
        Spinner spn = findViewById(R.id.frequenciaHist);
        ADPSpinner spinner = new ADPSpinner(spn, getApplicationContext(),R.array.escolhasSpinnerhitsRank);
        DBLocalController controller = new DBLocalController(getApplicationContext());
        Cursor cursor = controller.resgata();

        if (cursor.moveToFirst()) {
            do {
                user = new ModelUser(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9),
                        cursor.getString(10)

                );
            } while (cursor.moveToNext());

        }


        HashMap<String, String> params = new HashMap<>();
        params.put("login", user.getLogin());
        params.put("senha", user.getSenha());
        params.put("id", String.valueOf(user.getId()));

        ViewHistorico.PNRView request = new PNRView(API.URL_hist, params,API.CODE_POST_REQUEST);
        request.execute();


        Bundle put;
        String nometabela;
        put = getIntent().getExtras();
        nometabela = put.getString("chave");


    if(nometabela.equals("historico")){
        Log.d("caso", "onCreate: his caso 2 mano ");
        Log.d("caso", "onCreate: "+nometabela);
    }else if (nometabela.equals("ranking")){
        Log.d("caso", "onCreate: ran caso 1 mano");
        Log.d("caso", "onCreate: "+nometabela);
    }

        ImageView btnvolt = findViewById(R.id.backbtn);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent casa = new Intent(getApplicationContext(), ViewUser.class);
                startActivity(casa);
            }
        });
    }



    private void visualHist(JSONArray array) throws JSONException {
       /* DBLocalController controller = new DBLocalController(ViewHistorico.this);
        Cursor cursor = controller.resgata();
        if (cursor.moveToFirst()) {
            do {
                listaGenerica.add(new ModelUser(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9)

                ));
            } while (cursor.moveToNext());
        }
            aki e request server antigo
        DBJsonReqVoleiAPI jso = new DBJsonReqVoleiAPI(ViewHistorico.this,"http://192.168.0.11/CAMDIA/Query.php");
        jso.getList(new VolleyCallBack() {
            @Override
            public void onSuccess(ArrayList<ModelUser> listaGenerica) {
                listar = listaGenerica;
                Adapter = new AdaptadorH(ViewHistorico.this, R.layout.listitemforhistory, listar);
                listView = findViewById(R.id.listHist);
                listView.setAdapter(Adapter);
            }
        });*/

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            listar.add(new ModelUser(user.getNome(),"não utilizado", "não utilizado",
                    "não utilizado", obj.getInt("id"), "não utilizado", 0,
                    obj.getDouble("km"), 0, obj.getDouble("tempo"), "não utilizado"));

        }
        Adapter = new AdaptadorH(ViewHistorico.this, R.layout.listitemforhistory, listar);
        listView = findViewById(R.id.listHist);
        listView.setAdapter(Adapter);


    }


        public class PNRView extends AsyncTask<Void, Void, String> {
            String url;
            HashMap<String, String> params;
            int requestCode;


            public PNRView(String url, HashMap<String, String> params, int requestCode) {
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
                    visualHist(jsonObject.getJSONArray("users"));
                Log.d("testinggggggggggg", "onPostExecute: "+jsonObject.getJSONArray("users"));


                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Usuario não encontrado ", Toast.LENGTH_SHORT).show();

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


    }
