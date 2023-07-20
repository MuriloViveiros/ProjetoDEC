package com.example.decorartsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import DBhelper.ProdutosDAO;
import Model.Produtos;

public class MainActivity extends AppCompatActivity {
        ListView lista;
        ProdutosDAO DBhelper;
        ArrayList <Produtos> listviewProdutos;
        Produtos produto;
        ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRegistro = (Button) findViewById(R.id.buttonRegistro);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProdutosRegistro.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listviewProdutos);

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Produtos produtoEscolhido = (Produtos) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, ProdutosRegistro.class);
                i.putExtra("produto-escolhido", produtoEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                produto = (Produtos) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete  = menu.add("Deletar");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                DBhelper = new ProdutosDAO(MainActivity.this);
                DBhelper.deletarProduto(produto);
                DBhelper.close();

                carregarProduto();

                return true;
            }
        });
    }


    protected void onResume(){
        super.onResume();
        carregarProduto();

    }


    public void carregarProduto(){
        DBhelper = new ProdutosDAO(MainActivity.this);
        listviewProdutos = DBhelper.getLista();
        DBhelper.close();

        if (listviewProdutos != null){
            adapter = new ArrayAdapter<Produtos>(MainActivity.this, android.R.layout.simple_list_item_1, listviewProdutos);
            lista.setAdapter(adapter);

        }



    }

    }


