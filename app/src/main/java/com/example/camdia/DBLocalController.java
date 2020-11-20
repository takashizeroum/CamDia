package com.example.camdia;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBLocalController {
    private SQLiteDatabase db;
    private DBLocal banco;
    ModelUser user;

    public DBLocalController(Context context) {
        banco = new DBLocal(context);
    }
    public String insereDado(ModelUser usr) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", usr.getNome());
        valores.put("login", usr.getLogin());
        valores.put("empresa", usr.getEmpresa());
        valores.put("senha", usr.getSenha());
        valores.put("id", usr.getId());
        valores.put("des", usr.getDesc());
        valores.put("rank", usr.getRank());
        valores.put("km", usr.getKm());
        valores.put("compete", usr.getCompete());
        valores.put("tempo", usr.getTempo());
        valores.put("extras", usr.getExtras());
        resultado = db.insert("Memo", null, valores);

        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro Inserido com sucesso";
        }
    }

    public ModelUser resgata() {
        Cursor cursor;
        String[] campos = {"nome", "login", "empresa", "senha", "id", "des", "rank", "km", "compete", "tempo","extras"};
        db = banco.getReadableDatabase();
        cursor = db.query("Memo", campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
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

        db.close();
        return user;
    }
    public void delete(){
        db = banco.getWritableDatabase();
        db.delete("Memo",null, null);
        db.close();

    }
    public void update (ModelUser usr){
        db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", usr.getNome());
        valores.put("login", usr.getLogin());
        valores.put("empresa", usr.getEmpresa());
        valores.put("senha", usr.getSenha());
        valores.put("id", usr.getId());
        valores.put("des", usr.getDesc());
        valores.put("rank", usr.getRank());
        valores.put("km", usr.getKm());
        valores.put("compete", usr.getCompete());
        valores.put("tempo", usr.getTempo());
        valores.put("extras", usr.getExtras());

        db.update("Memo",valores,"id=?", new String[]{String.valueOf(usr.getId())});
        db.close();


    }
    public boolean verifica(int id) {
        Cursor cursor;
        boolean bol;
        String[] campos = {"nome", "login", "empresa", "senha", "id", "des", "rank", "km", "compete", "tempo","extras"};
        db = banco.getReadableDatabase();
        cursor = db.query("Memo", campos, "id = "+id, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        if (cursor.moveToFirst()) {
             bol = true;
        }else{bol =false;}

        db.close();

        return bol ;
    }
    public void novaMemo  () {
        db.execSQL("drop table Memo");

    }




}




