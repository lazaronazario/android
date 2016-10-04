package com.loremipsum.recifeguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loremipsum.recifeguide.model.Local;

public class LocalActivity extends AppCompatActivity implements CliqueiNoLocalListener{


    ListView mListView;
    private LocalAdapter mAdapter;




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
        setContentView(R.layout.activity_local);

        initViews();
    }

    private void initViews() {

        /*ArrayList<Local> locais = new ArrayList<Local>();

        Local a = new Local(R.drawable.chevrolet, "Chevrolet");
        Local b = new Local(R.drawable.fiat, "Fiat");
        Local c = new Local(R.drawable.volkswagen, "Volkswagen");
        Local d = new Local(R.drawable.ford, "Ford");

        locais.add(a);
        locais.add(b);
        locais.add(c);
        locais.add(d);*/

        mListView =  (ListView) findViewById(R.id.list_view);
        mAdapter = new LocalAdapter(this, countries, flags);
        mListView.setAdapter(mAdapter);
    }



    @Override
    public void LocalFoiClicado(Local local) {

    }
}
