package com.loremipsum.recifeguide.tasks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.directions.route.Routing;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.loremipsum.recifeguide.AcessoRest;
import com.loremipsum.recifeguide.LocalActivity;
import com.loremipsum.recifeguide.MainActivity;
import com.loremipsum.recifeguide.model.ContainerLocais;
import com.loremipsum.recifeguide.model.Local;
import com.loremipsum.recifeguide.util.ImageHelper;
import com.loremipsum.recifeguide.util.MapaHelper;

import java.util.ArrayList;

/**
 * Created by Suetonio on 27/10/2016.
 */

public class CarregarLocaisTask extends AsyncTask<Void, Void, ContainerLocais> {

    private MainActivity contexto = null;

    public CarregarLocaisTask(MainActivity contexto) {
        this.contexto = contexto;
    }

    @Override
    protected ContainerLocais doInBackground(Void... voids) {

        ContainerLocais containerLocais = null;
        try {
            AcessoRest acessoRest = new AcessoRest();
            String json = acessoRest.get("fnConsultaLocais");

            Gson gson = new Gson();
            containerLocais = gson.fromJson(json, ContainerLocais.class);

        } catch (Exception ex) {
            throw ex;
        }
        return containerLocais;
    }

    @Override
    protected void onPostExecute(ContainerLocais containerLocais) {

        if (containerLocais != null) {

            contexto.mLocais = containerLocais.locais;

            for (Local local : contexto.mLocais) {

                LatLng latLng = new LatLng(local.getLat(), local.getLng());

                Marker localMarker = contexto.map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(local.getNome()));

                localMarker.setIcon(BitmapDescriptorFactory.fromBitmap(ImageHelper.resizeMapIcon(contexto, local.getCategoriaLocal().toString().toLowerCase(), 50, 50)));
                localMarker.setPosition(latLng);

            }

        }
    }

}
