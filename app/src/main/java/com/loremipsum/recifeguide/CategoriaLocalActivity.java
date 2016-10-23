package com.loremipsum.recifeguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loremipsum.recifeguide.model.Local;

import java.util.ArrayList;

public class CategoriaLocalActivity extends AppCompatActivity{

    //private ContainerLocais containerLocais;
    //private ArrayList<Local> locais;
    private CategoriaLocaisAdapter mAdapter;
    private GridView gridView;

    public CategoriaLocalActivity(ArrayList<Local> locais){

    }

    public CategoriaLocalActivity(){

    }
    // Array of strings storing country names
    String[] countries ={
            "Museu",
            "Teatro",
            "Mercado Publico",
            "Feira Livre",
            "Pontes",
            "Compras",
            "Sem Categoria",
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags ={
            R.drawable.museu,
            R.drawable.teatro,
            R.drawable.mercadopublico,
            R.drawable.feiralivre,
            R.drawable.pontes,
            R.drawable.compras,
            R.drawable.semcategoria,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_local);
        new ConsultaLocaisTask().execute();

        initViews();
    }
        private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview);
        mAdapter = new CategoriaLocaisAdapter(this,countries, flags);
        gridView.setAdapter(mAdapter);


           gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mAdapter.getItem(position);
                    Intent intent = new Intent(parent.getContext(), LocalActivity2.class);
                    //intent.putExtra("locais", locais);
                    startActivity(intent);
                }
            });
    }
}
