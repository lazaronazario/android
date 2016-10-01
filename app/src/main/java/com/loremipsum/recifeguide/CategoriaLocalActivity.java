package com.loremipsum.recifeguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.loremipsum.recifeguide.model.CategoriaLocal;

import java.util.ArrayList;
import java.util.List;

public class CategoriaLocalActivity extends AppCompatActivity implements CliqueiNaCategoriaListener{

    private ArrayList<CategoriaLocal> categoriaLocais = new ArrayList<>();
    private CategoriaLocaisAdapter mAdapter;
    private GridView gridView;

    public CategoriaLocalActivity(List<CategoriaLocal> categoriaLocais){
        
        this.categoriaLocais = (ArrayList<CategoriaLocal>) categoriaLocais;
    }

    public CategoriaLocalActivity(){


    }
    // Array of strings storing country names
    String[] countries ={
            "Chevrolet",
            "Fiat",
            "Ford",
            "Volkswagen",
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags ={
            R.drawable.chevrolet,
            R.drawable.fiat,
            R.drawable.ford,
            R.drawable.volkswagen,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_local);

         initViews();
    }

        private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview);
        mAdapter = new CategoriaLocaisAdapter(this,countries, flags);
        gridView.setAdapter(mAdapter);
    }

    @Override
    public void CategoriaFoiClicada(CategoriaLocal categoriaLocal) {

    }
}
