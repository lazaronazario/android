package com.loremipsum.recifeguide;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.loremipsum.recifeguide.model.ContainerLocais;
import com.loremipsum.recifeguide.model.Local;
import com.loremipsum.recifeguide.model.Usuario;
import com.loremipsum.recifeguide.tasks.CarregarLocaisTask;
import com.loremipsum.recifeguide.tasks.CarregarRotaTask;
import com.loremipsum.recifeguide.util.AppConstants;
import com.loremipsum.recifeguide.util.ImageHelper;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.internal.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String PREF_NAME = "LoginPreference";
    String id;
    String nome;
    String email;
    ProfilePictureView ppf;
    TextView txtNome;
    TextView txtEmail;
    private static final String Login_Google = "G";
    private static final String Login_Facebook = "F";
    String strTipoLogin;
    Uri uriFotoGoogle;
    Usuario usuario;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String retorno;
    String envioJson;
    Gson gson;
    AccessToken accessToken;
    String strVerificado;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    MapFragment mapFragment;
    public GoogleMap map;
    Marker userMarker;
    boolean mRequestingLocationUpdates = false;
    FloatingActionMenu menuFab;
    FloatingActionButton rotaPreDefinida, criarRota;
    public static boolean isOnRoute = false;
    ProgressDialog progress = null;
    public static ArrayList<Local> mLocais = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        sharedPreferences = RecifeGuideApp.getApplication().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        strTipoLogin = sharedPreferences.getString("TIPO", "");
        strVerificado = sharedPreferences.getString("Verificado", "");

        menuFab = (FloatingActionMenu) findViewById(R.id.fab);
        rotaPreDefinida = (FloatingActionButton) findViewById(R.id.rotaPre);
        criarRota = (FloatingActionButton) findViewById(R.id.criarRota);
        progress = new ProgressDialog(this);

        rotaPreDefinida.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final CarregarRotaTask task = new CarregarRotaTask(MainActivity.this);

                progress.setTitle("Carregando");
                progress.setMessage("Carregando Rota..");
                progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
                progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        if (task != null && task.getStatus() == AsyncTask.Status.RUNNING)
                            task.cancel(true);
                        progress.dismiss();
                    }
                });
                progress.show();
                task.execute();


            }
        });
        criarRota.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CategoriaLocalActivity.class);
                startActivity(i);

            }
        });

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        strTipoLogin = sharedPreferences.getString("TIPO", "");
        strVerificado = sharedPreferences.getString("Verificado", "");





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        getLastLocation();

        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }


    public Location getCurrentLocation() {
        return mLastLocation;
    }

    private Location getLastLocation() {

        Location location = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    AppConstants.REQUEST_FINE_LOCATION);

            return null;
        }

        location = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        return location;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        sharedPreferences = RecifeGuideApp.getApplication().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        strTipoLogin = sharedPreferences.getString("TIPO", "");
        strVerificado = sharedPreferences.getString("Verificado", "");
        if (strTipoLogin.equals(Login_Facebook)) {
            if (AccessToken.getCurrentAccessToken() == null) {
                goLoginScreen();
            }
            nome = sharedPreferences.getString("NOME", "");
            email = sharedPreferences.getString("EMAIL", "");
            id = sharedPreferences.getString("ID", "");

            txtNome = (TextView) findViewById(R.id.meu_nome);
            txtNome.setText(nome);

            txtEmail = (TextView) findViewById(R.id.meu_email);
            txtEmail.setText(email);

            if (strVerificado.equals("OK") == false) {
                enviarUsuario();
            }

           ProfilePictureView ppf = (ProfilePictureView) findViewById(R.id.fbImage);
            ppf.setVisibility(View.VISIBLE);
            ppf.setProfileId(id);

            //txtNome = (TextView) findViewById(R.id.nome);
            //txtNome.setText("Bem Vindo " + nome );
        } else if (strTipoLogin.equals(Login_Google)) {
            nome = getIntent().getStringExtra("NOME");
            email = getIntent().getStringExtra("EMAIL");
            //uriFotoGoogle = Uri.parse(sharedPreferences.getString("FOTO", ""));

            txtNome = (TextView) findViewById(R.id.meu_nome);
            txtNome.setText(nome);

            txtEmail = (TextView) findViewById(R.id.meu_email);
            txtEmail.setText(email);


            new DownloadImageTask((ImageView) findViewById(R.id.gglImage))
                    .execute(sharedPreferences.getString("FOTO", ""));

            if (strVerificado.equals("OK") == false) {
                enviarUsuario();
            }
            //txtNome = (TextView) findViewById(R.id.nome);
            //txtNome.setText("Bem Vindo " + nome + "   " + email);

        }

        return true;
    }


    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mRequestingLocationUpdates) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }


    protected void stopLocationUpdates() {

        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        mRequestingLocationUpdates = false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                //abrirFragment();
                break;
            case R.id.rotas_turisticas:

                break;
            case R.id.ao_meu_redor:

                break;
            case R.id.outros_destinos:
                //Toast.makeText(this, "Foi clicado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, CategoriaLocalActivity.class);
                startActivity(i);
                break;
            case R.id.logout:
                this.logout();

                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void enviarUsuario() {
        try {
            usuario = new Usuario();
            usuario.setNome(sharedPreferences.getString("NOME", ""));
            usuario.setSocialId(sharedPreferences.getString("ID", ""));
            usuario.setEmail(sharedPreferences.getString("EMAIL", "Nao Encontrado"));
            gson = new Gson();
            envioJson = gson.toJson(usuario);
            AcessoRest acessoRest = new AcessoRest();
            retorno = acessoRest.get("fnInserirUsuario/" + usuario.getNome() + "," + usuario.getEmail() + "," + usuario.getSocialId());
            editor.putString("VERIFICADO", "OK");
            editor.commit();
        } catch (Exception e) {

        }
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        //SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences sharedPreferences = RecifeGuideApp.getApplication().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();

        goLoginScreen();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMinZoomPreference(5);
        map.setMaxZoomPreference(25);
        //  mLastLocation = getLastLocation();

        //  if (mLastLocation != null) {
        //      updateLocation(mLastLocation);
        //   }

        carregarLocaisNoMapa();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppConstants.REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Location location = getLastLocation();

                    if (location != null) {
                        updateLocation(location);
                    }

                    if (!mRequestingLocationUpdates) {

                        startLocationUpdates();
                    }
                } else {

                    Toast.makeText(this, "O aplicativo precisa da permissão de localização para rodar corretamente.", Toast.LENGTH_LONG);
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    AppConstants.REQUEST_FINE_LOCATION);

        }
        LocationRequest mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5 * 1000)        // 5 seconds, in milliseconds
                .setFastestInterval(5 * 1000); // 1 second, in milliseconds

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

        mRequestingLocationUpdates = true;
    }

    public void updateLocation(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (userMarker == null) {
            userMarker = map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Eu")
                    .icon(BitmapDescriptorFactory.fromBitmap(ImageHelper.resizeMapIcon(this,"ic_user", 100, 100))));
            updateCamera(latLng);

        } else {
            userMarker.setPosition(latLng);
        }

        if (isOnRoute && latLng.latitude != mLastLocation.getLatitude() && latLng.longitude != mLastLocation.getLongitude()) {
            updateCamera(latLng);
        }

        mLastLocation = location;
    }

    private void updateCamera(LatLng latLng) {

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        map.moveCamera(cameraUpdate);
    }

    public void startRoute() {
        isOnRoute = true;
        if (mLastLocation != null)
            updateLocation(mLastLocation);

        menuFab.close(true);
        progress.dismiss();
        //Toast.makeText(getApplicationContext(), " Rota iniciada!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        if (location != null)
            updateLocation(location);

    }

    private void carregarLocaisNoMapa()
    {

        ConnectivityManager connMgr = (ConnectivityManager)
        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new CarregarLocaisTask(this).execute();
        } else {
            Toast.makeText(this, "Sem conexão", Toast.LENGTH_LONG).show();
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setVisibility(View.VISIBLE);
            bmImage.setImageBitmap(result);
        }
    }
}
