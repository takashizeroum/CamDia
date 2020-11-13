package com.example.camdia;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBJsonReqVoleiAPI {
    Context context;
    ArrayList<ModelUser> arrayList = new ArrayList<>();
    ArrayList<ModelUser> Strarray = new ArrayList<>();
    String url_json;
    int contador = 0;

    public DBJsonReqVoleiAPI(Context context, String url_json) {
        this.context = context;
        this.url_json = url_json;
    }

    public void getList(final VolleyCallBack callBack) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url_json, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                            try {
                                while (contador < response.length()) {

                                JSONObject jsonObject = response.getJSONObject(contador);
                                ModelUser user = new ModelUser(jsonObject.getString("Nome"), jsonObject.getString("Login"),jsonObject.getString("Empresa"),
                                        jsonObject.getString("Senha"),jsonObject.getInt("Id"),jsonObject.getString("Descricao"),jsonObject.getInt("Rank"),
                                        jsonObject.getDouble("Km"),jsonObject.getInt("Comp"),jsonObject.getDouble("Tempo"),jsonObject.getString("extra"));
                                arrayList.add(user);


                                contador++;

                            } }catch (JSONException e) {
                                e.printStackTrace();

                            }

                            callBack.onSuccess(arrayList);
                    }



                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Erro ao carregar dados...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });


        ADPMySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);



    }
    /*
    public void getStr(final VolleyCallBack callBack, final String usuario, final String senha){

        StringRequest request = new StringRequest(Request.Method.POST, url_json,
                new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {

                        if(response.equals("0")) {
                            Toast.makeText(context, "Dados de usuario incorretos...", Toast.LENGTH_SHORT).show();
                        } else {
                            try {

                                while (contador < response.length()) {

                                    // OBTENEMOS LOS DATOS QUE DEVUELVE EL SERVIDOR
                                    Log.d("testing", "onResponse: respondeu str... " + response.toString());

                                    JSONObject jsonObject = new JSONObject();
                                    ModelUser user = new ModelUser(jsonObject.getString("Nome"), jsonObject.getString("Login"), jsonObject.getString("Empresa"),
                                            jsonObject.getString("Senha"), jsonObject.getInt("Id"), jsonObject.getString("Descricao"), jsonObject.getInt("Rank"),
                                            jsonObject.getDouble("Km"), jsonObject.getInt("Comp"), jsonObject.getDouble("Tempo"), jsonObject.getString("extra"));
                                    Strarray.add(user);

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        callBack.onSuccess(Strarray);
                        Log.d("testing", "onResponse: respondeu str... "+Strarray.size());
//problema ao infiar no array 

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // SE EJECUTA CUANDO ALGO SALIO MAL AL INTENTAR HACER LA CONEXION
                        Toast.makeText(context, "ERROR DE CONEXION...", Toast.LENGTH_SHORT).show();
                        Log.d("testing", "onResponse: erro con ");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // AQUI SE ENVIARAN LOS DATOS EMPAQUETADOS EN UN OBJETO MAP<clave, valor>
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", usuario);
                parametros.put("pass", senha);

                return parametros;

            }
        };

        ADPMySingleton.getInstance(context).addToRequestQue(request);
    }*/



}
