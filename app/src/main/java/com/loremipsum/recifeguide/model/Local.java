package com.loremipsum.recifeguide.model;

/**
 * Created by BREVE DEUS VEM on 24/09/2016.
 */

public class Local {

    private String imagem;
    private LocalDetalhes localDetalhes;

    public Local(String imagem, LocalDetalhes localDetalhes){

        this.setImagem(imagem);
        this.setLocalDetalhes(localDetalhes);
    }


    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDetalhes getLocalDetalhes() {
        return localDetalhes;
    }

    public void setLocalDetalhes(LocalDetalhes localDetalhes) {
        this.localDetalhes = localDetalhes;
    }
}
