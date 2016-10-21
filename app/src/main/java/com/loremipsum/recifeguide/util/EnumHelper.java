package com.loremipsum.recifeguide.util;

/**
 * Created by BREVE DEUS VEM on 02/10/2016.
 */

public class EnumHelper {

    public static String ObterDescricao(CategoriaLocal cat) {

        String desc = "";
        switch (cat) {
            case COMPRAS:
                desc = "Compras";
                break;
            case SEMCATEGORIA:
                desc = "Sem Categoria";
                break;
            case FEIRALIVRE:
                desc = "Feira Livre";
                break;
            case MERCADOPUBLICO:
                desc = "Mercado Público";
                break;
            case MUSEU:
                desc = "Museu";
                break;
            case PONTES:
                desc = "Pontes";
                break;
            case TEATRO:
                desc = "Teatro";
                break;
            default:
                break;

        }

        return desc;
    }

    public static String ObterDescricaoIdioma(TipoIdioma idioma){
        String desc = "";
        switch (idioma) {
            case PT:
                desc = "Português";
                break;
            case EN:
                desc = "Inglês";
                break;
        }
        return desc;
    }
}
