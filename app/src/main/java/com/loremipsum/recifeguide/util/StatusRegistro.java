package com.loremipsum.recifeguide.util;

/**
 *
 * @author Christian.Palmer
 */
public enum StatusRegistro {

    INATIVO(0), ATIVO(1);

    public final int valor;

    StatusRegistro(int valor) {
        this.valor = valor;
    }
}
