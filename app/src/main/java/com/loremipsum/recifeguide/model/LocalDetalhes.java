package com.loremipsum.recifeguide.model;

import com.loremipsum.recifeguide.EntidadeBase;
import com.loremipsum.recifeguide.util.TipoIdioma;

/**
 * Created by BREVE DEUS VEM on 24/09/2016.
 */
public class LocalDetalhes extends EntidadeBase{

    private String nome;
    private TipoIdioma tipoIdioma;
    private String descricao;
    private Local local;

    public LocalDetalhes(){
        this.local = new Local();
    }


    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipoIdioma
     */
    public TipoIdioma getTipoIdioma() {
        return tipoIdioma;
    }

    /**
     * @param tipoIdioma the tipoIdioma to set
     */
    public void setTipoIdioma(TipoIdioma tipoIdioma) {
        this.tipoIdioma = tipoIdioma;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the local
     */
    public Local getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(Local local) {
        this.local = local;
    }


}
