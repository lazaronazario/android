package com.loremipsum.recifeguide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loremipsum.recifeguide.model.CategoriaLocal;

import java.util.List;

/**
 * Created by BREVE DEUS VEM on 24/09/2016.
 */

public class CategoriaLocaisAdapter extends RecyclerView.Adapter<CategoriaLocaisAdapter.MeuViewHolder> {

    private List<CategoriaLocal> categoriaLocals;
    private Context context;

    public CategoriaLocaisAdapter(List<CategoriaLocal> categoriaLocais) {
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder {

        public TextView nome;
        public ImageView imgCatLocal;

        public MeuViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nomeCategoria);
            imgCatLocal = (ImageView) view.findViewById(R.id.imgCategoria);
        }
    }


    public CategoriaLocaisAdapter(Context context,List<CategoriaLocal> categoriaLocals) {
        this.categoriaLocals = categoriaLocals;
        this.context = context;
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria_local, parent, false);

        return new MeuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeuViewHolder holder, int position) {
        CategoriaLocal categoriaLocal = categoriaLocals.get(position);
        holder.nome.setText(categoriaLocal.getNome());
        Glide.with(context).load(categoriaLocals.get(position)).into(holder.imgCatLocal);
    }

    @Override
    public int getItemCount() {
        return categoriaLocals.size();
    }
}

