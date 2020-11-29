package com.example.camdia;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;


public class ViewLogin extends AppCompatActivity {

    //ArrayList<ModelUser> lista = new ArrayList<ModelUser>();
    EditText login;
    EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Botões e seus listeners
        Button btnEntrar = findViewById(R.id.btnEntrar);


        login = findViewById(R.id.txtlogLogin);
        senha = findViewById(R.id.txtlogSenha);

        btnEntrar.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {
            entrar();

        }});


    }


    private void entrar () {



            final String log = login.getText().toString();
            final String senh= senha.getText().toString();


            HashMap<String, String> params = new HashMap<>();
            params.put("login", log);
            params.put("senha", senh);

            PerformNetworkRequestt request = new PerformNetworkRequestt(API.URL_veri, params,API.CODE_POST_REQUEST);
            request.execute();



    }




public void valida (JSONArray array) throws JSONException {
    ModelUser us = null;

    for (int i = 0; i < array.length(); i++) {
        JSONObject obj = array.getJSONObject(i);
        us = new ModelUser(obj.getString("nome"),obj.getString("login"),obj.getString("empresa"),obj.getString("senha"),obj.getInt("id"),obj.getString("desc"),obj.getInt("rank"),obj.getDouble("km"),obj.getInt("comp"),obj.getDouble("temp"),obj.getString("extra"));


        DBLocalController control = new DBLocalController(getBaseContext());
        control.resgata();

        if (control.verifica(us.getId())==true){
            control.update(us);
            Log.d("asass", "valida: "+us.getEmpresa());

        }else{
            control.delete();
            control.insereDado(us);


        }
        Intent go = new Intent(ViewLogin.this, ViewPrincipal.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fade_in,R.anim.move_direita);
        ActivityCompat.startActivity(ViewLogin.this,go,activityOptionsCompat.toBundle());





    }





}




    public class PerformNetworkRequestt extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;





        public PerformNetworkRequestt(String url, HashMap<String, String> params, int requestCode) {
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
                valida(jsonObject.getJSONArray("users"));
                Log.d("djsdjsdsjdhsjdhsjdhsdj", "onPostExecute: "+jsonObject);


            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "As credenciais são inválidas ", Toast.LENGTH_SHORT).show();

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
