package com.elsafty.Recipe.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    public static Bitmap laodImage(String imageURL) {
        HttpURLConnection httpURLConnection = null;
        Bitmap bmp = null;
        URL url;
        try {
            url = new URL(imageURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            bmp = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        return bmp;
    }
}
