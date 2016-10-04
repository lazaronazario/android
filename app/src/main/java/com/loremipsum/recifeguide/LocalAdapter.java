package com.loremipsum.recifeguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BREVE DEUS VEM on 01/10/2016.
 */

public class LocalAdapter extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public LocalAdapter(LocalActivity localActivity, String[] countries, int[] flags) {
        // TODO Auto-generated constructor stub
        result=countries;
        context=localActivity;
        imageId=flags;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView nome;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item_local, null);
        holder.nome =(TextView) rowView.findViewById(R.id.nome_local);
        holder.img=(ImageView) rowView.findViewById(R.id.imgLocal);

        holder.nome.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        /*rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(context, "You Clicou "+result[position], Toast.LENGTH_LONG).show();
            }
        });*/

        return rowView;
    }

}
