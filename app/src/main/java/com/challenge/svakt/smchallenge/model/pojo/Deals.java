package com.challenge.svakt.smchallenge.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by svakt on 01/05/2017.
 */

public class Deals implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("offer_description_line3")
    @Expose
    private String offerDescription;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("image_app")
    @Expose
    private ImageApp image_app;

    public Deals(Parcel in) {
        name = in.readString();
        offerDescription = in.readString();
        price = in.readString();
        value = in.readString();
        image_app = in.readParcelable(ImageApp.class.getClassLoader());
    }

    public static final Creator<Deals> CREATOR = new Creator<Deals>() {
        @Override
        public Deals createFromParcel(Parcel in) {
            return new Deals(in);
        }

        @Override
        public Deals[] newArray(int size) {
            return new Deals[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(offerDescription);
        dest.writeString(price);
        dest.writeString(value);
        dest.writeParcelable(image_app,flags);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ImageApp getImage_app() {
        return image_app;
    }

    public void setImage_app(ImageApp image_app) {
        this.image_app = image_app;
    }
}
