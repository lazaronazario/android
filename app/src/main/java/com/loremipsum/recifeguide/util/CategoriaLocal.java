package com.loremipsum.recifeguide.util;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BREVE DEUS VEM on 02/10/2016.
 */

public enum CategoriaLocal {
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
    PONTE(5),
    @SerializedName("6")
    COMPRAS(6);

    public final int valor;

    CategoriaLocal(int valor) {
        this.valor = valor;
    }
}
