package com.loremipsum.recifeguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loremipsum.recifeguide.model.Local;

public class LocalDetalheActivity extends AppCompatActivity {

    ShareActionProvider mActionProvider;
    private String mNome;
    private String mDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detalhe);

        Intent it = getIntent();

        mNome = it.getStringExtra("nome");
        mDescricao = it.getStringExtra("descricao");

        TextView textViewNome = (TextView) findViewById(R.id.nomeLocal);
        TextView textViewDescricao = (TextView) findViewById(R.id.descricaoLocal);
        textViewNome.setText(mNome);
        textViewDescricao.setText(mDescricao);

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
