package com.example.camdia;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewUser extends AppCompatActivity {

    TextView nome, desc,rank, km;
    String urlimg;
    ModelUser user ;
    Switch sw ;
    int comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //adapta switch
        sw = findViewById(R.id.btncompperfil);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                 comp = 1;

                } else {
                    comp = 0 ;

                }
                DBLocalController controller = new DBLocalController(getApplicationContext());
                ModelUser user = controller.resgata();

                HashMap<String, String> params = new HashMap<>();
                params.put("comp", String.valueOf(comp));
                params.put("id", String.valueOf(user.getId()));

                RequestNET request = new RequestNET(API.URL_UPDATE, params,API.CODE_POST_REQUEST);
                request.execute();
                Log.d("testing", "onCheckedChanged: url :  "+params);
            }
        });
        DBLocalController controller = new DBLocalController(getApplicationContext());
        ModelUser usr = controller.resgata();

        HashMap<String, String> params = new HashMap<>();
        params.put("login", usr.getLogin());
        params.put("senha", usr.getSenha());

        ViewUser.PNRViewP request = new ViewUser.PNRViewP(API.URL_veri, params,API.CODE_POST_REQUEST);
        request.execute();



        //escutador do botão voltar
        final ImageView btnvolt = findViewById(R.id.backbtnus);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), ViewPrincipal.class));
            }
        });
    }

    //botões de histórico e ranking
    public void historico(View view) {
        Intent go = new Intent(ViewUser.this, ViewHistorico.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fade_in,R.anim.move_direita);
        ActivityCompat.startActivity(ViewUser.this,go,activityOptionsCompat.toBundle());

    }
    public void ranking(View view) {
        Intent go = new Intent(ViewUser.this, ViewRanking.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fade_in,R.anim.move_direita);
        ActivityCompat.startActivity(ViewUser.this,go,activityOptionsCompat.toBundle());


    }

    public void VUCarrega(JSONArray array) throws JSONException {

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            user = new ModelUser(obj.getString("nome"), obj.getString("login"), obj.getString("empresa")
                    , obj.getString("senha"), obj.getInt("id"), obj.getString("desc"),
                    obj.getInt("rank"), obj.getDouble("km"), obj.getInt("comp"), obj.getDouble("temp"), obj.getString("extra"));

        }


        nome = findViewById(R.id.usrNameUsr);
        desc = findViewById(R.id.usrempUsr);
        rank = findViewById(R.id.usrcolocaUsr);
        km = findViewById(R.id.usrkmUsr);

        nome.setText(user.getNome());
        desc.setText(user.getEmpresa());
        rank.setText(String.valueOf(user.getRank()));
        km.setText(user.getKm().toString());
        urlimg = user.getExtras();

        // chama classe que carrega imagem de perfil
        ImageView i = findViewById(R.id.imgPerfil);
        VoleiAPImg img = new VoleiAPImg();
        img.carregaImg(i, getApplicationContext(),urlimg);

        //verifica switch
        if (user.getCompete()==1){
            sw.setChecked(true);
        }else{
            sw.setChecked(false);
        }


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
                VUCarrega(jsonObject.getJSONArray("users"));
                Log.d("djsdjsdsjdhsjdhsjdhsdj", "onPostExecute: "+jsonObject);


            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Falha ao Sincronizar ", Toast.LENGTH_SHORT).show();

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





