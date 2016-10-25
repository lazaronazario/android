package com.loremipsum.recifeguide.model;

import com.google.gson.annotations.SerializedName;
import com.loremipsum.recifeguide.model.EntidadeBase;
import com.loremipsum.recifeguide.util.CategoriaLocal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BREVE DEUS VEM on 24/09/2016.
 */

public class Local extends EntidadeBase {

    @SerializedName("IdLocal")
    private int id;

    @SerializedName("Latitude")
    private double lat;

    @SerializedName("Longitude")
    private double lng;

    @SerializedName("Imagem")
    private String imagem;

    @SerializedName("HorarioIniFuncionamento")
    private String horarioInicio;

    @SerializedName("HorarioFimFuncionamento")
    private String horarioFim;

    @SerializedName("ApenasDiaUtil")
    private boolean apenasDiaUtil;

    @SerializedName("CategoriaLocal")
    private CategoriaLocal categoriaLocal;

    @SerializedName("LocalDetalhes")
    private ArrayList<LocalDetalhes> listaDetalhes;


    /**
     * @return the lat
     * @param chevrolet
     * @param s
     */
    public Local(int chevrolet, String s) {
        this.listaDetalhes = new ArrayList<>();
    }

    public Local(int id) {
        this.id = id;
        this.listaDetalhes = new ArrayList<>();
    }

    public Local(){


    }


    public String getNome()
    {
        if (this.listaDetalhes.size() > 0 && this.listaDetalhes.get(0) != null && this.listaDetalhes.get(0).getNome() != null ) {
            return this.listaDetalhes.get(0).getNome();
        }
        else {
            return "";
        }

    }

    public String getDescricao()
    {
        if (this.listaDetalhes.size() > 0 && this.listaDetalhes.get(0) != null && this.listaDetalhes.get(0).getDescricao() != null ) {
            return this.listaDetalhes.get(0).getDescricao();
        }
        else {
            return "";
        }

    }

    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * @return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the horarioInicio
     */
    public String getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * @param horarioInicio the horarioInicio to set
     */
    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    /**
     * @return the horarioFim
     */
    public String getHorarioFim() {
        return horarioFim;
    }

    /**
     * @param horarioFim the horarioFim to set
     */
    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    /**
     * @return the apenasDiaUtil
     */
    public boolean isApenasDiaUtil() {
        return apenasDiaUtil;
    }

    /**
     * @param apenasDiaUtil the apenasDiaUtil to set
     */
    public void setApenasDiaUtil(boolean apenasDiaUtil) {
        this.apenasDiaUtil = apenasDiaUtil;
    }

    /**
     * @return the categoriaLocal
     */
    public CategoriaLocal getCategoriaLocal() {
        return categoriaLocal;
    }

    /**
     * @param categoriaLocal the categoriaLocal to set
     */
    public void setCategoriaLocal(CategoriaLocal categoriaLocal) {
        this.categoriaLocal = categoriaLocal;
    }

    /**
     * @return the listaDetalhes
     */
    public ArrayList<LocalDetalhes> getListaDetalhes() {
        return listaDetalhes;
    }

    /**
     * @param listaDetalhes the listaDetalhes to set
     */
    public void setListaDetalhes(ArrayList<LocalDetalhes> listaDetalhes) {
        this.listaDetalhes = listaDetalhes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    /*private int imagem;
    private String nome;
    private LocalDetalhes localDetalhes;
    private CategoriaLocal categoriaLocal;

    public Local(int imagem, String nome){


    }

    public Local(){

        this.setImagem(imagem);
        this.setNome(nome);
        //this.setLocalDetalhes(localDetalhes);
        //this.setCategoriaLocal(categoriaLocal);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaLocal getCategoriaLocal() {
        return categoriaLocal;
    }

    public void setCategoriaLocal(CategoriaLocal categoriaLocal) {
        this.categoriaLocal = categoriaLocal;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public LocalDetalhes getLocalDetalhes() {
        return localDetalhes;
    }

    public void setLocalDetalhes(LocalDetalhes localDetalhes) {
        this.localDetalhes = localDetalhes;
    }*/


    @Override
    public boolean equals(Object o)
    {
        return o!=null &&
                (o instanceof  Local) &&
                ((Local)o).id == this.id;

    }

}
