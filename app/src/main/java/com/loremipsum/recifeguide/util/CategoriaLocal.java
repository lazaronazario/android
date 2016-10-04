package com.loremipsum.recifeguide.util;

/**
 * Created by BREVE DEUS VEM on 02/10/2016.
 */

public enum CategoriaLocal {

    SEMCATEGORIA(0), MUSEU(1), TEATRO(2), MERCADOPUBLICO(3), FEIRALIVRE(4),PONTE(5), COMPRAS(6);

    public final int valor;

    CategoriaLocal(int valor) {
        this.valor = valor;
    }
}
