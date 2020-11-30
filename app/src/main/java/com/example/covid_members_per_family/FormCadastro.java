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
    EditText editText_email, editText_nome, editText_bairro;
    Button btn_salvar;
    Familias editarFamilia, familia;
    FamiliasDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        familia = new Familias();
        dbHelper = new FamiliasDB(FormCadastro.this);

        Intent intent = getIntent();
        editarFamilia = (Familias) intent.getSerializableExtra("familia-escolhida");

        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_nome = (EditText) findViewById(R.id.editText_nome);
        editText_bairro = (EditText) findViewById(R.id.editText_bairro);

        btn_salvar = (Button) findViewById(R.id.btn_salvar);

        if (editarFamilia != null){
            btn_salvar.setText("Modificar");
            editText_email.setText(editarFamilia.getEmail());
            editText_nome.setText(editarFamilia.getNome()+"");
            editText_bairro.setText(editarFamilia.getBairro()+"");
            familia.setId(editarFamilia.getId());
        }else{
            btn_salvar.setText("Cadastrar");
        }

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                familia.setEmail(editText_email.getText().toString());
                familia.setNome(editText_nome.getText().toString());
                familia.setBairro(editText_bairro.getText().toString());

                if(btn_salvar.getText().toString().equals("Cadastrar")){
                    dbHelper.salvarFamilia(familia);
                    dbHelper.close();
                }else{
                    dbHelper.alterarFamilia(familia);
                    dbHelper.close();
                }
            }
        });

    }
}