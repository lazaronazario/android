package com.loremipsum.recifeguide.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Christian.Palmer on 24/10/2016.
 */

public class MapaHelper {

    /*  GoogleMap mMap;

      public MapaHelper(GoogleMap map)
      {
          this.mMap = map;
      }*/
    public static MarkerOptions createStringMarker(String content) {

        Paint paint = new Paint();
        paint.setTextSize(24);
        paint.setTextAlign(Paint.Align.CENTER);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(50, 50, conf);
        Canvas canvas = new Canvas(bmp);

        canvas.drawText(content, 0, 50, paint); // paint defines the text color, stroke width, size
        return new MarkerOptions()
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2))
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .anchor(0.5f, 1);


    }




}
