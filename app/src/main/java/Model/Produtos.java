package Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Produtos implements Serializable {
    private Long Id;
    private String Produto;
    private String Descricao;

    @NonNull
    @Override
    public String toString() {
        return Produto.toString();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String nomeproduto) {
        Produto = nomeproduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
