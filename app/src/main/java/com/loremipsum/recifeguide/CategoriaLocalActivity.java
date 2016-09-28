package com.loremipsum.recifeguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loremipsum.recifeguide.model.CategoriaLocal;

import java.util.ArrayList;

public class CategoriaLocalActivity extends AppCompatActivity implements CliqueiNaCategoriaListener{

    //ViewPager mViewPager = null;
    public static final String EXTRA_CATEGORIA_LOCAL = "categoria_local";
    private ArrayList<CategoriaLocal> categoriaLocais = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoriaLocaisAdapter mAdapter;




    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
    };

    private final String android_image_urls[] = {
            "R.drawable.chevrolet",
            "R.drawable.chevrolet",
            "R.drawable.chevrolet",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_local);
        initViews();
    }


    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        mAdapter = new CategoriaLocaisAdapter(categoriaLocais);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        dadosTeste();
    }

    private ArrayList dadosTeste(){

        //ArrayList categoria = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++) {
            CategoriaLocal categoriaLocal = new CategoriaLocal();
            categoriaLocal.setNome(android_version_names[i]);
            categoriaLocal.setImgCatLocal(android_image_urls[i]);
            categoriaLocais.add(categoriaLocal);
        }
        return categoriaLocais;
    }


    @Override
    public void CategoriaFoiClicada(CategoriaLocal categoriaLocal) {

    }
}
