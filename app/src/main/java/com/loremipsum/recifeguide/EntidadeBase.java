package com.loremipsum.recifeguide;

import com.google.gson.annotations.SerializedName;
import com.loremipsum.recifeguide.util.StatusRegistro;

/**
 *
 * @author Christian
 */
public abstract class EntidadeBase {

    @SerializedName("IdLocal")
    private int id;

    @SerializedName("StatusRegistro")
    private StatusRegistro statusRegistro;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the statusRegistro
     */
    public StatusRegistro getStatusRegistro() {
        return statusRegistro;
    }

    /**
     * @param statusRegistro the statusRegistro to set
     */
    public void setStatusRegistro(StatusRegistro statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

}

