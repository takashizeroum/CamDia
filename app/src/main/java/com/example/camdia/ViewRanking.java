package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;

public class ViewRanking extends AppCompatActivity {

    AdaptadorR Adapter;
    private ArrayList<ModelUser> listar = new ArrayList<>();
    ListView listView;
    private Spinner spn;
    ModelUser us ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        HashMap<String, String> params = new HashMap<>();
        params.put("login", "login");
        params.put("senha", "senha");


        ViewRanking.PNRView request = new ViewRanking.PNRView(API.URL_READ, params,API.CODE_POST_REQUEST);
        request.execute();


        ImageView btnvolt = findViewById(R.id.backusr);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            Intent casa = new Intent(getApplicationContext(), ViewUser.class);
                startActivity(casa);
            }
        });



    }

    private void visualRank(JSONArray array) throws JSONException {

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            listar.add(us = new ModelUser(obj.getString("nome"),obj.getString("login"),obj.getString("empresa"),obj.getString("senha"),
                    obj.getInt("id"),obj.getString("desc"),obj.getInt("rank"),obj.getDouble("km"),obj.getInt("comp"),
                    obj.getDouble("temp"),obj.getString("extra")));


        }
        Adapter = new AdaptadorR(ViewRanking.this, R.layout.listitemforuser, listar);
        listView = findViewById(R.id.listRanking);
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
                Log.d("testinggggggggggg", "onPostExecute: "+s);
                final JSONObject jsonObject = new JSONObject(s);

                visualRank(jsonObject.getJSONArray("users"));



            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Nenhum usuario não encontrado ", Toast.LENGTH_SHORT).show();

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