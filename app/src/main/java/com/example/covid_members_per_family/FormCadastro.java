package com.example.covid_members_per_family;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.covid_members_per_family.BDHelper.FamiliasDB;
import com.example.covid_members_per_family.model.Pessoas;

public class FormCadastro extends AppCompatActivity {
    EditText editText_email, editText_nome, editText_bairro;
    Button btn_salvar;
    Pessoas editarPessoa, pessoa;
    FamiliasDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        pessoa = new Pessoas();
        dbHelper = new FamiliasDB(FormCadastro.this);

        Intent intent = getIntent();
        editarPessoa = (Pessoas) intent.getSerializableExtra("pessoa-escolhida");

        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_nome = (EditText) findViewById(R.id.editText_nome);
        editText_bairro = (EditText) findViewById(R.id.editText_bairro);

        btn_salvar = (Button) findViewById(R.id.btn_salvar);

        if (editarPessoa != null){
            btn_salvar.setText("Modificar");
            editText_email.setText(editarPessoa.getEmail());
            editText_nome.setText(editarPessoa.getNome()+"");
            editText_bairro.setText(editarPessoa.getBairro()+"");
            pessoa.setId(editarPessoa.getId());
        }else{
            btn_salvar.setText("Cadastrar");
        }

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setEmail(editText_email.getText().toString());
                pessoa.setNome(editText_nome.getText().toString());
                pessoa.setBairro(editText_bairro.getText().toString());

                if(btn_salvar.getText().toString().equals("Cadastrar")){
                    dbHelper.salvarPessoa(pessoa);
                    dbHelper.close();
                }else{
                    dbHelper.alterarPessoa(pessoa);
                    dbHelper.close();
                }
            }
        });

    }
}