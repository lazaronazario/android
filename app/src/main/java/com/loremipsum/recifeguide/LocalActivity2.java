package com.loremipsum.recifeguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loremipsum.recifeguide.model.Local;

import java.util.ArrayList;

public class LocalActivity2 extends AppCompatActivity implements CliqueiNoLocalListener{


    ListView mListView;
    private LocalAdapter mAdapter;
    private ArrayList<Local> locais = null;
    //private ContainerLocais containerLocais = null;
    //private ArrayList<ContainerLocais> containerLocais;

    public LocalActivity2(){

    }

    public LocalActivity2(ArrayList<Local> locais){
        this.locais = locais;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        //locais = (ArrayList<Local>) getIntent().getSerializableExtra("locais");
        initViews();

    }

    private void initViews() {
        mListView =  (ListView) findViewById(R.id.list_view);
        mAdapter = new LocalAdapter(this, locais);
        mListView.setAdapter(mAdapter);
    }


    @Override
    public void LocalFoiClicado(Local local) {

    }
}
