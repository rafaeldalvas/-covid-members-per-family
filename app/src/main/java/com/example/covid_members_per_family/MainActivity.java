package com.example.covid_members_per_family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_cadastros);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Familias familiaEscolhida = (Familias) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormCadastro.class);
                i.putExtra("familia-escolhida", familiaEscolhida);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                familia = (Familias) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este registro");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dbHelper = new FamiliasDB(MainActivity.this);
                dbHelper.deletarFamilia(familia);
                dbHelper.close();
                carregarFamilia();
                return true;
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