package com.loremipsum.recifeguide;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.loremipsum.recifeguide.model.ContainerLocais;
import com.loremipsum.recifeguide.model.Local;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Suetonio on 04/10/2016.
 */

class ConsultaLocaisAsyncTask extends AsyncTask<Void, Void, ArrayList<Local>> {

    @Override
    protected ArrayList<Local> doInBackground(Void... voids) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://10.0.0.102/WebService.svc/fnConsultaLocais")
                .build();

        ContainerLocais containerLocais = new ContainerLocais();

        try{
            Response response = client.newCall(request).execute();
            String json = response.body().string();

            Gson gson = new Gson();
            containerLocais = gson.fromJson(json, ContainerLocais.class);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return containerLocais.locais;
    }

    protected void onPostExecute(ArrayList<Local> locais) {
        //
    }
}
