package com.example.camdia;
import android.content.Intent;
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
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        login = findViewById(R.id.txtlogLogin);
        senha = findViewById(R.id.txtlogSenha);

        btnEntrar.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {
            entrar();

        }});
        btnCadastrar.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {cadasta();}});

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



    //abrir formulario cadstro
    private void cadasta() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro, null);

        final EditText cmplogin = view.findViewById(R.id.cadtxtlogin);
        final EditText cmpnome = view.findViewById(R.id.cadtxtnome);
        final EditText cmpsenha = view.findViewById(R.id.cadtxtSenha);
        final EditText cmpemp = view.findViewById(R.id.cademp);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastro");
        builder.setView(view);
        final AlertDialog dia = builder.create();
        dia.show();

        //sair form cadstro
        view.findViewById(R.id.btnSaircad).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg) {
                dia.dismiss();
            }
        });


        //insere bd
       view.findViewById(R.id.btncadcad).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg) {

                final String log = cmplogin.getText().toString();
                final String nome = cmpnome.getText().toString();
                final String senh= cmpsenha.getText().toString();
                final String emp = cmpemp.getText().toString();



//                DBLocalController db = new DBLocalController(getApplicationContext());
  //              db.insereDado(new ModelUser(nome,log,emp,senh,1,"",0,10.6,1,5.3));

                Toast.makeText(getApplicationContext(), "cadastrado na tabela ", Toast.LENGTH_SHORT).show();
                dia.dismiss();
            }
        });
}
public void valida (JSONArray array) throws JSONException {
    ModelUser us = null;

    for (int i = 0; i < array.length(); i++) {
        JSONObject obj = array.getJSONObject(i);
        us = new ModelUser(obj.getString("nome"),obj.getString("login"),obj.getString("empresa"),obj.getString("senha"),obj.getInt("id"),obj.getString("desc"),obj.getInt("rank"),obj.getDouble("km"),obj.getInt("comp"),obj.getDouble("temp"),obj.getString("extra"));
        Log.d("testingggg", "valida: "+us.getLogin());

        startActivity(new Intent(ViewLogin.this, ViewPrincipal.class));


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
