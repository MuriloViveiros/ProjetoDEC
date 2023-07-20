package com.example.decorartsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DBhelper.ProdutosDAO;
import Model.Produtos;

public class ProdutosRegistro extends AppCompatActivity {


    EditText textboxProduto , textboxDescricao;
    Button buttonAdicionar;
    Produtos EditarProduto,produto;
    ProdutosDAO DBhelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        produto = new Produtos();
        DBhelper = new ProdutosDAO(ProdutosRegistro.this);

        Intent  intent = getIntent();
        EditarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        textboxProduto = (EditText) findViewById(R.id.textboxProduto);
        textboxDescricao = (EditText) findViewById(R.id.textboxDescricao);

        buttonAdicionar = (Button) findViewById(R.id.buttonAdicionar);

        if (EditarProduto != null){
            buttonAdicionar.setText("Modificar");

            textboxProduto.setText(EditarProduto.getProduto());
            textboxDescricao.setText(EditarProduto.getDescricao());

            produto.setId(EditarProduto.getId());

        }else {
            buttonAdicionar.setText("Cadastrar");
        }
        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            produto.setProduto(textboxProduto.getText().toString());
            produto.setDescricao(textboxDescricao.getText().toString());

            if (buttonAdicionar.getText().toString().equals("Cadastrar")){
            DBhelper.salvarProduto(produto);
            DBhelper.close();
            }else {
                DBhelper.alterarProduto(produto);
                DBhelper.close();
            }

            }
        });

    }
}