package com.elsafty.Recipe;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {
    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
    private String name;
    private String quantity;
    private String type;

    public Ingredients(String name, String quantity, String type) {
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    protected Ingredients(Parcel in) {
        name = in.readString();
        quantity = in.readString();
        type = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(quantity);
        parcel.writeString(type);
    }
}
