package DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.Produtos;

public class ProdutosDAO extends SQLiteOpenHelper {

    private static final String DATABASE="DBprodutos";
    private static final int version = 1;

    public ProdutosDAO (Context context ){
        super(context, DATABASE,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String produto = "Create table produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeproduto text not null, descricao Text not null)";
   db.execSQL(produto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "Drop Table IF EXISTS produtos";
        db.execSQL(produto);

    }
    public void salvarProduto(Produtos produto){
        ContentValues values = new ContentValues();
         values.put("nomeproduto",produto.getProduto());
         values.put("descricao",produto.getDescricao());

         getWritableDatabase().insert("produtos",null,values);

    }

    public void alterarProduto(Produtos produto){
        ContentValues values = new ContentValues();
        values.put("nomeproduto",produto.getProduto());
        values.put("descricao",produto.getDescricao());

        String [] args = {produto.getId().toString()};
        getWritableDatabase().update("produtos",values,"id=?",args);


    }

    public void deletarProduto(Produtos produto){
        String [] args = {produto.getId().toString()};
        getWritableDatabase().delete("produtos","id=?",args);

    }

    public ArrayList<Produtos> getLista(){
        String [] columns={"id","nomeproduto", "descricao"};
        Cursor cursor = getWritableDatabase().query("produtos",columns,null,null,null,  null,null, null);
        ArrayList<Produtos> produtos = new ArrayList<Produtos>();
        while (cursor.moveToNext()){
            Produtos produto = new Produtos();
            produto.setId(cursor.getLong(0));
            produto.setProduto(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produtos.add(produto);


        }

        return produtos;
    }
}
