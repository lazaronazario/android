package com.loremipsum.recifeguide.model;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by Christian.Palmer on 24/10/2016.
 */

public class Rota {
    @SerializedName("IdRota")
    private int id;

    @SerializedName("Nome")
    private String nome;

    @SerializedName("Descricao")
    private String descricao;

    @SerializedName("Tamanho")
    private String tamanho;


    @SerializedName("Imagem")
    private String imagem;


    @SerializedName("IdUsuario")
    private int idUsuario;
    @SerializedName("TipoRota")
    private int TipoRota;


    @SerializedName("Usuario")
    private Usuario usuario;

    @SerializedName("ListaLocais")
    private ArrayList<Local> listaLocais;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipoRota() {
        return TipoRota;
    }

    public void setTipoRota(int tipoRota) {
        TipoRota = tipoRota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Local> getListaLocais() {
        return listaLocais;
    }

    public void setListaLocais(ArrayList<Local> listaLocais) {
        this.listaLocais = listaLocais;
    }



    public Local[] obterMelhorTrajeto(final Location currentLocation)
    {
        TreeSet<Local> tree = new TreeSet<Local>(new Comparator<Local>() {
            @Override
            public int compare(Local local1, Local local2) {

                Location l1 = new Location("l1");
                l1.setLatitude(local1.getLat());
                l1.setLongitude(local1.getLng());

                Location l2 = new Location("l2");
                l2.setLatitude(local2.getLat());
                l2.setLongitude(local2.getLng());


                   return (int) (l1.distanceTo(currentLocation) - l2.distanceTo(currentLocation));
            }
        });

        tree.addAll(this.listaLocais);

                return tree.toArray(new Local[this.listaLocais.size()]);
    }
}
