package com.loremipsum.recifeguide;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.loremipsum.recifeguide.model.ContainerLocais;

/**
 * Created by BREVE DEUS VEM on 22/10/2016.
 */

public class ConsultaLocaisTask extends AsyncTask<Void, Void, ContainerLocais> {

    @Override
    protected ContainerLocais doInBackground(Void... voids) {

        AcessoRest acessoRest = new AcessoRest();
        String json = acessoRest.get("fnConsultaLocais");

        Gson gson = new Gson();
        ContainerLocais containerLocais = gson.fromJson(json, ContainerLocais.class);

        return containerLocais;
    }

    @Override
    protected void onPostExecute(ContainerLocais containerLocais) {

    }
}
