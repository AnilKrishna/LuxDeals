package com.challenge.svakt.smchallenge.model;

import com.challenge.svakt.smchallenge.model.pojo.Deals;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sunny on 01/05/2017.
 */

public class DealsList  {
    @SerializedName("data")
    @Expose
    private ArrayList<Deals> deals = new ArrayList<>();

    /**
     * @return The deals
     */
    public ArrayList<Deals> getDeals() {
        return deals;
    }

    /**
     * @param deals The deals
     */
    public void setDeals(ArrayList<Deals> deals) {
        this.deals = deals;
    }
}
