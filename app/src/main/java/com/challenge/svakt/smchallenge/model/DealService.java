package com.challenge.svakt.smchallenge.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sunny on 01/05/2017.
 */

public interface DealService {
    @GET("deallist.json")
    Call<DealsList> getDealsList();
}
