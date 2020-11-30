package com.example.covid_members_per_family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.covid_members_per_family.BDHelper.FamiliasDB;
import com.example.covid_members_per_family.model.Familias;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    FamiliasDB dbHelper;
    ArrayList<Familias> listview_cadastros;
    Familias familia;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listview_cadastros);

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FormCadastro.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarFamilia();
    }

    public void carregarFamilia(){
        dbHelper = new FamiliasDB(MainActivity.this);
        listview_cadastros = dbHelper.getLista();
        dbHelper.close();

        if (listview_cadastros != null){
            adapter = new ArrayAdapter<Familias>(MainActivity.this, android.R.layout.simple_list_item_1, listview_cadastros);
            lista.setAdapter(adapter);
        }
    }
}