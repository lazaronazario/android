package com.loremipsum.recifeguide.tasks;

import android.location.Location;
import android.os.AsyncTask;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.loremipsum.recifeguide.AcessoRest;
import com.loremipsum.recifeguide.MainActivity;
import com.loremipsum.recifeguide.R;
import com.loremipsum.recifeguide.model.Local;
import com.loremipsum.recifeguide.model.Rota;
import com.loremipsum.recifeguide.util.MapaHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian.Palmer on 24/10/2016.
 */
public class CarregarRotaTask extends AsyncTask<Void, Void, Rota[]> {


    MapaHelper mapaHelper = null;
    private MainActivity activity = null;
    Routing routing = null;


    public CarregarRotaTask(MainActivity context) {
        activity = context;

        mapaHelper = new MapaHelper();

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        if (routing != null && routing.getStatus() == Status.RUNNING)
            routing.cancel(true);
    }

    @Override
    protected Rota[] doInBackground(Void... voids) {


        try {
            AcessoRest acessoRest = new AcessoRest();
            String json = acessoRest.get("fnConsultaRotas");

            Gson gson = new Gson();
            Rota[] rotas = gson.fromJson(json, Rota[].class);
            return rotas;
        }

        catch (Exception ex)
        {

            throw ex;

        }

    }

    @Override
    protected void onPostExecute(Rota[] rotas) {


        if (rotas != null && rotas.length > 0) {

            Rota rota = rotas[0];

            Location currentLocation = activity.getCurrentLocation();
            Local[] locais = rota.obterMelhorTrajeto(currentLocation);

            LatLng start = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            ArrayList<LatLng> waypoints = new ArrayList<>();
            waypoints.add(start);

            if (locais != null) {
                for (int i = 0; i < locais.length - 1; i++) {

                    Local local = locais[i];
                    LatLng latLng = new LatLng(local.getLat(), local.getLng());
                    waypoints.add(latLng);
                }

                waypoints.add(new LatLng(locais[locais.length - 1].getLat(), (locais[locais.length - 1].getLng())));

                Routing.Builder routingBuilder = new Routing.Builder()
                        .travelMode(Routing.TravelMode.WALKING)
                        .withListener(routingListener)
                        .waypoints(waypoints);


                routing = routingBuilder.build();
                routing.execute();

            }


        }
    }


    RoutingListener routingListener = new RoutingListener() {
        @Override
        public void onRoutingFailure(RouteException e) {

        }

        @Override
        public void onRoutingStart() {

        }

        @Override
        public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
            List<Polyline> polylines = new ArrayList<>();
            final int[] COLORS = new int[]{R.color.colorPrimaryDark, R.color.colorPrimary, R.color.com_facebook_blue, R.color.colorAccent, R.color.primary_dark_material_light};

            if (polylines.size() > 0) {
                for (Polyline poly : polylines) {
                    poly.remove();
                }
            }

            polylines = new ArrayList<>();
            //add route(s) to the map.
            for (int i = 0; i < route.size(); i++) {

                //In case of more than 5 alternative routes
                int colorIndex = i % COLORS.length;

                PolylineOptions polyOptions = new PolylineOptions();
                polyOptions.color(activity.getResources().getColor(COLORS[colorIndex]));
                polyOptions.width(10 + i * 3);
                polyOptions.addAll(route.get(i).getPoints());
                Polyline polyline = activity.map.addPolyline(polyOptions);
                polylines.add(polyline);

                // Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
            }

            activity.startRoute();
        }

        @Override
        public void onRoutingCancelled() {

        }

    };


}
