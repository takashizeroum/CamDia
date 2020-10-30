package com.example.camdia;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBJsonReqVoleiAPI {
    Context context;
    ArrayList<ModelUser> arrayList = new ArrayList<ModelUser>();
    String url_json = "http://192.168.0.26/CAMDIA/contatos.php";
    int contador = 0;

    public DBJsonReqVoleiAPI(Context context) {
        this.context = context;
    }

    public ArrayList<ModelUser> getList() {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url_json, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                            try {
                                while (contador < response.length()) {
                                Log.d("testing", "onResponse: "+"response tem tamanho"+response.length());
                                JSONObject jsonObject = response.getJSONObject(contador);
                                ModelUser user = new ModelUser(jsonObject.getString("Nome"), jsonObject.getString("Login"),jsonObject.getString("Empresa"),
                                        jsonObject.getString("Senha"),jsonObject.getInt("Id"),jsonObject.getString("Descricao"),jsonObject.getInt("Rank"),
                                        jsonObject.getDouble("Km"),jsonObject.getInt("Comp"),jsonObject.getDouble("Tempo"));
                                arrayList.add(user);
                                Log.d("testing", "onResponse: "+"array tem tamanho"+arrayList.size());

                                contador++;

                            } }catch (JSONException e) {
                                e.printStackTrace();

                            }
                        Log.d("testing", "onResponse: "+"array tentativa"+arrayList.size());

                    }



                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Erro ao carregar dados...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        Log.d("testing", "onResponse: "+" no fim array tem:" +arrayList.size());
        ADPMySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);

        return arrayList;

    }

}
