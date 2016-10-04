package com.loremipsum.recifeguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final String PREF_NAME = "LoginPreference";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private String email;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton btnLoginGgl;
    private GoogleSignInOptions signInOptions;
    private static final int REQUEST_CODE = 100;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        int status = sharedPreferences.getInt("STATUS", 0);
        String tipoLogin = sharedPreferences.getString("TIPO", "");


        if (status == 1 && tipoLogin.equals("F")) {
            goMainScreenFacebook();
        }else if (status == 1 && tipoLogin.equals("G")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            signInFacebook();
            signInGoogle();

        }


    }

    private void signInFacebook(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.fbLogin);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {


                goMainScreenFacebook();
            }



            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInGoogle(){
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        btnLoginGgl = (SignInButton) findViewById(R.id.gglLogin);

        btnLoginGgl.setSize(SignInButton.SIZE_WIDE);
        btnLoginGgl.setScopes(signInOptions.getScopeArray());
        btnLoginGgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinItent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signinItent,REQUEST_CODE);

            }
        });
    }

    private void goMainScreenFacebook() {
        Profile perfil = com.facebook.Profile.getCurrentProfile();
        String nome = perfil.getName();
        String id = perfil.getId();

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("STATUS",1);
        editor.putString("TIPO","F");
        editor.putString("NOME",nome.toString());
        editor.putString("ID",id);
        editor.putString("EMAIL",email);
        editor.commit();


        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("NOME",nome.toString());
        intent.putExtra("ID",id);
        intent.putExtra("EMAIL",email);

        startActivity(intent);

    }

    private void goMainScreenGoogle(GoogleSignInAccount usuario) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("STATUS",1);
        editor.putString("TIPO","G");
        editor.putString("NOME",usuario.getDisplayName());
        editor.putString("ID",usuario.getId());
        editor.putString("EMAIL",usuario.getEmail());
        editor.putString("FOTO",usuario.getPhotoUrl().toString());
        editor.commit();

        intent.putExtra("NOME",usuario.getDisplayName());
        intent.putExtra("ID",usuario.getId());
        intent.putExtra("EMAIL",usuario.getEmail());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            account = result.getSignInAccount();
            goMainScreenGoogle(account);
        }else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }

    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
