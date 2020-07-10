package com.elsafty.Recipe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable {
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    private String name;
    private ArrayList<String> steps;
    private ArrayList<Ingredients> ingredients;
    private String imageUrl;
    private String originalURL;

    public Recipe(String name, ArrayList<String> steps, ArrayList<Ingredients> ingredients, String imageUrl, String originalURL) {
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
        this.originalURL = originalURL;
    }

    protected Recipe(Parcel in) {
        ingredients = in.createTypedArrayList(new Creator<Ingredients>() {
            @Override
            public Ingredients createFromParcel(Parcel parcel) {
                return new Ingredients(parcel);
            }

            @Override
            public Ingredients[] newArray(int size) {
                return new Ingredients[size];
            }
        });
        //ingredients = in.readArrayList(Ingredients.class.getClassLoader());
        name = in.readString();
        steps = in.createStringArrayList();
        imageUrl = in.readString();
        originalURL = in.readString();
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(ingredients);
        parcel.writeString(name);
        parcel.writeStringList(steps);
        parcel.writeString(imageUrl);
        parcel.writeString(originalURL);
    }
}
