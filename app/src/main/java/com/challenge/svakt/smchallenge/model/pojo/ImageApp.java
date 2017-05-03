package com.challenge.svakt.smchallenge.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by svakt on 2/05/2017.
 */

public class ImageApp implements Parcelable {
    @SerializedName("image")
    @Expose
    private Image image;
    protected ImageApp(Parcel in) {
        image = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<ImageApp> CREATOR = new Parcelable.Creator<ImageApp>() {
        @Override
        public ImageApp createFromParcel(Parcel in) {
            return new ImageApp(in);
        }

        @Override
        public ImageApp[] newArray(int size) {
            return new ImageApp[size];
        }
    };

    public Image getImage() {
        return image;
    }

    public void setImage(Image imageParam) {
        image = imageParam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(image,flags);
    }
}
