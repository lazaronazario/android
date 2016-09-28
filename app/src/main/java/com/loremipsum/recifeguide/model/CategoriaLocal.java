package com.loremipsum.recifeguide.model;

import java.util.List;

/**
 * Created by BREVE DEUS VEM on 24/09/2016.
 */

public class CategoriaLocal {

    private String nome;
    private String imgCatLocal;
    private List<Local> locais;

    public CategoriaLocal() {

    }

    public CategoriaLocal(String nome, String imgCatLocal) {

        this.setNome(nome);
        this.setImgCatLocal(imgCatLocal);
        this.setLocais(locais);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImgCatLocal() {
        return imgCatLocal;
    }

    public void setImgCatLocal(String imgCatLocal) {
        this.imgCatLocal = imgCatLocal;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }
}
