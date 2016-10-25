package com.loremipsum.recifeguide.model;

import com.google.gson.annotations.SerializedName;
import com.loremipsum.recifeguide.util.StatusRegistro;

/**
 *
 * @author Christian
 */
public abstract class EntidadeBase {



    @SerializedName("StatusRegistro")
    private StatusRegistro statusRegistro;

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

