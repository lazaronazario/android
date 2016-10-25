package com.loremipsum.recifeguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();

        if (status == 1 && tipoLogin.equals("F")) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else if (status == 1 && tipoLogin.equals("G")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            signInFacebook();
            signInGoogle();

        }



     /*   Intent intentXD = new Intent(LoginActivity.this,MainActivity.class);

        startActivity(intentXD );*/
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

                Log.v("profile track", (DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(loginResult.getAccessToken().getExpires())));
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {

                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String id = object.getString("id");
                                   goMainScreenFacebook(name,id,email);

                    /*write  your code  that is to be executed after successful login*/


                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }



            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebook erro", error.getMessage());

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

    private void goMainScreenFacebook(String p_nome,String p_id, String p_email) {
        //Profile perfil = com.facebook.Profile.getCurrentProfile();
        //String nome = perfil.getName();
        //String id = perfil.getId();

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("STATUS",1);
        editor.putString("TIPO","F");
        editor.putString("NOME",p_nome);
        editor.putString("ID",p_id);
        editor.putString("EMAIL",p_email);
        editor.commit();


        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("NOME",p_nome.toString());
        intent.putExtra("ID",p_id);
        intent.putExtra("EMAIL",p_email);

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
        //editor.putString("FOTO",usuario.getPhotoUrl().toString());
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
