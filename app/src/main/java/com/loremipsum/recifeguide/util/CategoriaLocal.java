package com.loremipsum.recifeguide.util;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by BREVE DEUS VEM on 02/10/2016.
 */

public enum CategoriaLocal implements Serializable{
    @SerializedName("0")
    SEMCATEGORIA(0),
    @SerializedName("1")
    MUSEU(1),
    @SerializedName("2")
    TEATRO(2),
    @SerializedName("3")
    MERCADOPUBLICO(3),
    @SerializedName("4")
    FEIRALIVRE(4),
    @SerializedName("5")
    PONTES(5),
    @SerializedName("6")
    COMPRAS(6);

    public final int valor;

    CategoriaLocal(int valor) {
        this.valor = valor;
    }
}
