package com.example.camdia;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;




public class RequestNET extends AsyncTask<Void, Void, String> {


        String url;
        HashMap<String, String> params;
        int requestCode;
        int metodo;





        public RequestNET(String url, HashMap<String, String> params, int requestCode,int metodo) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
            this.metodo = metodo;

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

                switch(metodo){
                    case 1:
                        ViewLogin lg = new ViewLogin();
                        lg.valida(jsonObject.getJSONArray("users"));
                    break;


                }




            } catch (JSONException e) {

                e.printStackTrace();

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





