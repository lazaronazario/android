package com.loremipsum.recifeguide;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loremipsum.recifeguide.model.ContainerLocais;
import com.loremipsum.recifeguide.model.Local;
import com.loremipsum.recifeguide.util.CategoriaLocal;
import com.loremipsum.recifeguide.util.EnumHelper;

import java.util.ArrayList;

public class LocalActivity extends AppCompatActivity implements CliqueiNoLocalListener {


    ListView mListView;
    private LocalAdapter mAdapter;
    private ArrayList<Local> mLocais = new ArrayList<>();
    ConsultaLocaisTask mTask;
    Enum filtroCategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        Intent intent = getIntent();
        CategoriaLocal categoria = (CategoriaLocal) intent.getSerializableExtra("categoriaLocal");
        //Toast.makeText(getBaseContext(), categoria.toString(), Toast.LENGTH_SHORT).show();
        filtroCategoria = categoria;


        getSupportActionBar().setTitle("Lista de "+ EnumHelper.ObterDescricao(categoria));

        if (mLocais.size() == 0 && mTask == null) {
            mTask = new ConsultaLocaisTask();
            mTask.execute();
        }else if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            carregarLocais();
        }else {
            Toast.makeText(this, R.string.carregarLocais, Toast.LENGTH_LONG).show();
        }
        initViews();
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new LocalAdapter(this, mLocais);
        mListView.setAdapter(mAdapter);
    }


    @Override
    public void LocalFoiClicado(Local local) {

    }

    class ConsultaLocaisTask extends AsyncTask<Void, Void, ContainerLocais> {

        @Override
        protected ContainerLocais doInBackground(Void... voids) {

            AcessoRest acessoRest = new AcessoRest();
            String json = acessoRest.get("fnConsultaLocais");

            try {
                Gson gson = new Gson();
                ContainerLocais containerLocais = gson.fromJson(json, ContainerLocais.class);

                return containerLocais;

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ContainerLocais containerLocais) {
            mLocais.clear();
            if (containerLocais != null) {
                for (Local local : containerLocais.locais) {
                    if (filtroCategoria == local.getCategoriaLocal()){
                        mLocais.add(local);
                   }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void carregarLocais() {

        ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (mTask == null) {
                mTask = new ConsultaLocaisTask();
                mTask.execute();
            } else {
                Toast.makeText(this, "sem conexao", Toast.LENGTH_LONG).show();
            }
        }
    }
}
