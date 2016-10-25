package com.loremipsum.recifeguide;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import com.bumptech.glide.Glide;
import com.loremipsum.recifeguide.model.Local;

public class LocalDetalheActivity extends AppCompatActivity {

    ShareActionProvider mActionProvider;
    private String mNome;
    private String mDescricao;
    private String mImagem;
    private String mHorarioAbertura;
    private String mHorarioFechamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detalhe);

        Intent it = getIntent();

        mNome = it.getStringExtra("nome");
        mDescricao = it.getStringExtra("descricao");
        mImagem = it.getStringExtra("imagem");
        mHorarioAbertura = it.getStringExtra("horarioAbertura");
        mHorarioFechamento = it.getStringExtra("horarioFechamento");
        try {
            TextView textViewNome = (TextView) findViewById(R.id.nomeLocal);
            TextView textViewDescricao = (TextView) findViewById(R.id.descricaoLocal);
            ImageView imageViewImagem = (ImageView) findViewById(R.id.imagemLocal);
            TextView textViewHorarioAbertura = (TextView) findViewById(R.id.horarioAbertura);
            TextView textViewHorarioFechamento = (TextView) findViewById(R.id.horarioFechamento);
            textViewNome.setText(mNome);
            textViewDescricao.setText(mDescricao);
            textViewHorarioAbertura.setText(mHorarioAbertura.split(" ")[1]);
            textViewHorarioFechamento.setText(mHorarioFechamento.split(" ")[1]);
        }catch(Exception ex){}

        //Glide.with(this).load().into(mImagem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detalhe_activity_menu, menu);

        MenuItem shared = menu.findItem(R.id.action_shared);
        mActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shared);
        Intent i = new Intent(Intent.ACTION_SEND);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, mNome);
        mActionProvider.setShareIntent(i);

        return super.onCreateOptionsMenu(menu);
    }
}
