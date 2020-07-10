package com.elsafty.Recipe.Utilities;

import android.content.Context;

import com.elsafty.Recipe.Ingredients;
import com.elsafty.Recipe.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataUtils {
    public static final ArrayList<String> imageURLs = new ArrayList<>();
    private static final String TAG = "DataUtils";

    public static ArrayList<Recipe> getRecipes(Context context) throws JSONException {
        ArrayList<Ingredients> mIngredients = null;
        ArrayList<Recipe> mRecipes = new ArrayList<>();
        ArrayList<String> mSteps = null;

        JSONArray jsonArray = new JSONArray(loadRecipesFromJson(context));
        for (int i = 0; i < jsonArray.length(); i++) {
            mSteps = new ArrayList<>();
            mIngredients = new ArrayList<>();
            JSONObject recipes = jsonArray.getJSONObject(i);
            String name = recipes.getString("name");
//            Log.d(TAG, "getRecipes: Name "+name);
            JSONArray ingredients = recipes.getJSONArray("ingredients");
            for (int j = 0; j < ingredients.length(); j++) {
                JSONObject ingridsObject = ingredients.getJSONObject(j);
                String ingridsQuantity = ingridsObject.getString("quantity");
                String ingridsName = ingridsObject.getString("name");
                String ingridsType = ingridsObject.getString("type");
                mIngredients.add(new Ingredients(ingridsQuantity, ingridsName, ingridsType));
            }
//            Log.d(TAG, "getRecipes: mIngredients.size() = "+mIngredients.size());
            JSONArray steps = recipes.getJSONArray("steps");
            for (int k = 0; k < steps.length(); k++) {
                mSteps.add(steps.getString(k));
            }
//            Log.d(TAG, "getRecipes: mSteps.size() = "+mSteps.size());
            String imageUrl = recipes.getString("imageURL");
//            Log.d(TAG, "getRecipes: imageUrl " + imageUrl);
            String originalURL;
            if (recipes.has("originalURL")) {
                originalURL = recipes.getString("originalURL");
            } else {
                originalURL = "";
            }
//            Log.d(TAG, "getRecipes: originalURL " + originalURL);
            imageURLs.add(imageUrl);
            mRecipes.add(new Recipe(name, mSteps, mIngredients, imageUrl, originalURL));
        }
        return mRecipes;
    }


    private static String loadRecipesFromJson(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("recipes.json");
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
