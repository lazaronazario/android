package com.loremipsum.recifeguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loremipsum.recifeguide.model.Local;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BREVE DEUS VEM on 20/10/2016.
 */

public class LocalAdapter2 extends ArrayAdapter<Local> {


    public LocalAdapter2(Context context, ArrayList<Local> locais) {
        super(context, 0, locais);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Local local = getItem(position);
        //locais.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            if(getContext() != null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_local, parent, false);
            }
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(getContext()).load(local.getImagem()).into(viewHolder.imagemView);
        viewHolder.textTitulo.setText(local.getNome());

        return convertView;
    }

    static class ViewHolder{

        @BindView(R.id.imgLocal)
        ImageView imagemView;
        @BindView(R.id.nome_local)
        TextView textTitulo;

        public ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }

}
