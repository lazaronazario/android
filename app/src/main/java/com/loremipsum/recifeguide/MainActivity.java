package com.loremipsum.recifeguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

public class MainActivity extends AppCompatActivity {
    String id;
    String nome;
    String email;
    ProfilePictureView ppf;
    TextView txtNome;
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }

        nome = getIntent().getStringExtra("NOME");
        email = getIntent().getStringExtra("EMAIL");
        id = getIntent().getStringExtra("ID");
        ppf = (ProfilePictureView) findViewById(R.id.fbProfilePicture);
        ppf.setProfileId(id);

        txtNome = (TextView) findViewById(R.id.nome);
        txtNome.setText("Bem Vindo " + nome);

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
