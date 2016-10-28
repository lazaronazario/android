package com.loremipsum.recifeguide;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loremipsum.recifeguide.model.ContainerLocais;
import com.loremipsum.recifeguide.model.Local;
import com.loremipsum.recifeguide.tasks.CarregarLocaisTask;
import com.loremipsum.recifeguide.util.CategoriaLocal;
import com.loremipsum.recifeguide.util.EnumHelper;

import java.util.ArrayList;

public class LocalActivity extends AppCompatActivity implements CliqueiNoLocalListener {


    ListView mListView;
    private LocalAdapter mAdapter;
    private ArrayList<Local> mLocais =  MainActivity.mLocais;
    private ArrayList<Local> mLocaisFiltrados = new ArrayList<>();
    Enum filtroCategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        Intent intent = getIntent();
        CategoriaLocal categoria = (CategoriaLocal) intent.getSerializableExtra("categoriaLocal");
        filtroCategoria = categoria;

        getSupportActionBar().setTitle("Lista de "+ EnumHelper.ObterDescricao(categoria));

        carregarLocais();

        initViews();

    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new LocalAdapter(this, mLocaisFiltrados);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Local local = mLocaisFiltrados.get(i);
                Intent it = new Intent(getBaseContext(), LocalDetalheActivity.class);
                it.putExtra("nome",local.getNome());
                it.putExtra("descricao",local.getDescricao());
                it.putExtra("imagem",local.getImagem());
                it.putExtra("horarioAbertura",local.getHorarioInicio());
                it.putExtra("horarioFechamento",local.getHorarioFim());
                startActivity(it);
            }
        });
    }


    @Override
    public void LocalFoiClicado(Local local) {
    }

    private void carregarLocais() {

        if(mLocais != null)
        {
            for (Local local : mLocais) {
               if (filtroCategoria == local.getCategoriaLocal()){
                    mLocaisFiltrados.add(local);
               }
            }
            // mAdapter.notifyDataSetChanged();
        }

    }
}
