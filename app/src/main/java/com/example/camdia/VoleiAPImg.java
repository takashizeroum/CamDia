package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class VoleiAPImg extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    //Carrega imagem de perfil usuario
    public void carregaImg(final ImageView i, final Context c, final String server_url) {


        final ImageRequest imageRequest = new ImageRequest(server_url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        i.setImageBitmap(response);
                    }
                }, 0, 0,
                ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c,
                                "Erro ao visualizar a imagem"+server_url,

                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });
        ADPMySingleton.getInstance(c).addToRequestQue(imageRequest);


    }


}