package com.example.amazingplaces.network

import com.example.amazingplaces.model.detail_model.Details
import com.example.amazingplaces.model.place_model.Places
import com.example.amazingplaces.model.place_model.PlacesModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpApi {
    /**
     * It returns a Single of Places
     */
    @GET("places")
    fun getPlaces(): Single<Places>

    /**
     * This function takes a string as a parameter and returns a single observable of type Details
     *
     * @param id The id of the place you want to get details for.
     */
    @GET("places/{UUID}")
    fun getPlaceById(@Path("UUID")id: String): Single<Details>

}