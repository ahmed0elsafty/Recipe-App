package com.elsafty.Recipe;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.elsafty.Recipe.Utilities.NetworkUtils;

import java.lang.ref.WeakReference;

public class loadBitmapAsynTask extends AsyncTask<String,Void, Bitmap> {
    WeakReference<ImageView> ivRecipe;

    public loadBitmapAsynTask(ImageView ivRecipe) {
        this.ivRecipe =new WeakReference<>(ivRecipe);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String imageURL = strings[0];
        return NetworkUtils.laodImage(imageURL);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ivRecipe.get().setImageBitmap(bitmap);
        cancel(true);
    }
}
