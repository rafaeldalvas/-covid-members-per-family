package com.example.covid_members_per_family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.covid_members_per_family.BDHelper.FamiliasDB;
import com.example.covid_members_per_family.model.Familias;

public class FormCadastro extends AppCompatActivity {
    EditText editText_sobrenome, editText_familiares, editText_infectados;
    Button btn_salvar;
    Familias editarFamilia, familia;
    FamiliasDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        dbHelper = new FamiliasDB(FormCadastro.this);

        Intent intent = getIntent();
        editarFamilia = (Familias) intent.getSerializableExtra("familia-escolhida");

        editText_sobrenome = (EditText) findViewById(R.id.editText_sobrenome);
        editText_familiares = (EditText) findViewById(R.id.editText_familiares);
        editText_infectados = (EditText) findViewById(R.id.editText_infectados);

        btn_salvar = (Button) findViewById(R.id.btn_salvar);

        if (editarFamilia != null){
            btn_salvar.setText("Modificar");
        }else{
            btn_salvar.setText("Cadastrar");
        }

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                familia.setSobrenome(editText_sobrenome.getText().toString());
                familia.setNumFamiliares(Integer.parseInt(editText_familiares.getText().toString()));
                familia.setNumInfectados(Integer.parseInt(editText_infectados.getText().toString()));

                if(btn_salvar.getText().toString().equals("Cadastrar")){
                    dbHelper.salvarFamilia(familia);
                    dbHelper.close();
                }
            }
        });

    }
}