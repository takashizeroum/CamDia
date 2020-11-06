package com.example.camdia;

import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ADPSpinner  {

    Spinner spinner;
    Context contexto;
    int escolha;
    private ArrayAdapter<CharSequence> adapter;
    public ADPSpinner(Spinner spinner, Context contexto,int escolha) {

        this.spinner = spinner;
        this.escolha = escolha;
        this.contexto = contexto;
        Log.d("testing", "ADPSpinner: "+escolha);
        adapter = ArrayAdapter.createFromResource(contexto,
        escolha, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //escutador de eventos do spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d("testes", "onItemSelected:  testou mlk"+pos+"id"+id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}

