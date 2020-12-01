package com.example.covid_members_per_family.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.covid_members_per_family.model.Pessoas;

import java.util.ArrayList;

public class FamiliasDB extends SQLiteOpenHelper {

    private static final String DATABASE = "dbfamilias";
    private static final int VERSION =1;

    public FamiliasDB (Context context){
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String familia = "CREATE TABLE familia(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sobrenome TEXT NOT NULL, numFamiliares TEXT NOT NULL, numInfectados TEXT NOT NULL);";
        db.execSQL(familia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String familia = "DROP TABLE IF EXISTS familia";
        db.execSQL(familia);
    }

    public void salvarPessoa (Pessoas familia){
        ContentValues values = new ContentValues();

        values.put("sobrenome", familia.getEmail());
        values.put("numFamiliares", familia.getNome());
        values.put("numInfectados", familia.getBairro());

        getWritableDatabase().insert("familia",null,values);
    }

    public void editarPessoa (Pessoas familia){
        ContentValues values = new ContentValues();

        values.put("sobrenome", familia.getEmail());
        values.put("numFamiliares", familia.getNome());
        values.put("numInfectados", familia.getBairro());

        String [] args = {familia.getId().toString()};

        getWritableDatabase().update("familia",values, "id=?",args);
    }

    public void deletarPessoa (Pessoas familia) {
        String [] args = {familia.getId().toString()};

        getWritableDatabase().delete("familia", "id=?",args);
    }

    public ArrayList<Pessoas> getLista(){
        String [] columns = {"id", "sobrenome", "numFamiliares", "numInfectados"};
        Cursor cursor = getWritableDatabase().query("familia", columns, null,null,null, null, null, null);
        ArrayList<Pessoas> familias = new ArrayList<Pessoas>();

        while (cursor.moveToNext()){
            Pessoas familia = new Pessoas();
            familia.setId(cursor.getLong(0));
            familia.setEmail(cursor.getString(1));
            familia.setNome(cursor.getString(2));
            familia.setBairro(cursor.getString(3));

           familias.add(familia);
        }
        return familias;
    }
}
