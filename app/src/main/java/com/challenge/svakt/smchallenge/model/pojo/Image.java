package com.challenge.svakt.smchallenge.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by svakt on 2/05/2017.
 */

public class Image implements Parcelable {
    @SerializedName("base_uri")
    @Expose
    private String base_uri;

    @SerializedName("base_url_identifier")
    @Expose
    private String base_url_identifier;

    protected Image(Parcel in) {
        base_uri = in.readString();
        base_url_identifier = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getBase_uri() {
        return base_uri;
    }

    public String getBase_url_identifier() {
        return base_url_identifier;
    }

    public void setBase_uri(String base_uriParam) {
        base_uri = base_uriParam;
    }



    public void setBase_url_identifier(String base_url_identifierParam) {
        base_url_identifier = base_url_identifierParam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(base_uri);
        dest.writeString(base_url_identifier);
    }
}
