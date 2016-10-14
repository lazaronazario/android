package com.loremipsum.recifeguide;

import android.os.StrictMode;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Dannyllo on 28/09/2016.
 */
public class AcessoRest {
    private final String IP = "10.0.0.102";
    //private final String PORT = "";
    private StringBuilder url;


    public String get(String complemento){
        url = new StringBuilder();
        url.append("http://");
        url.append(IP);
        //url.append(":");
        //url.append(PORT);
        url.append("/WebService.svc/");
        url.append(complemento);

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(url.toString()).build();


                Response response = client.newCall(request).execute();
                return response.body().string();

        }catch (Exception e){
            e.printStackTrace();
        }
        //HttpClient httpClient = new DefaultHttpClient();
        return "";
    }

    public String post(String complemento,String bodyMessage){
        url = new StringBuilder();
        url.append("http://");
        url.append(IP);
        //url.append(":");
        //url.append(PORT);
        url.append("/WebService.svc/");
        url.append(complemento);

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url.toString()).post(RequestBody.create(MediaType.parse("application/json"),bodyMessage)).build();


            Response response = client.newCall(request).execute();
            return response.body().string();

        }catch (Exception e){
            e.printStackTrace();
        }
        //HttpClient httpClient = new DefaultHttpClient();
        return "";
    }

}
