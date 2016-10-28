package com.loremipsum.recifeguide.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.loremipsum.recifeguide.MainActivity;

/**
 * Created by Suetonio on 27/10/2016.
 */

public class ImageHelper {

    public static Bitmap resizeMapIcon(Context contexto, String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(contexto.getResources(), contexto.getResources().getIdentifier(iconName, "drawable", contexto.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

}
