package com.example.amazingplaces.api

import android.content.Context
import com.example.amazingplaces.model.detail_model.Details
import com.example.amazingplaces.model.place_model.Places
import com.example.amazingplaces.model.place_model.PlacesModel
import io.reactivex.Observable
import io.reactivex.Single

interface ApiService {
    /**
     * Returns true if the device has a ping command.
     *
     * @param context The context of the application.
     */
    fun hasPing(context: Context): Boolean
    /**
     * It returns a Single that emits a Places object
     */
    fun getPlace(): Single<Places>
    /**
     * `getPlaceById` returns a `Single` that emits a `Details` object
     *
     * @param id The id of the place you want to get details for.
     */
    fun getPlaceById(id: String): Single<Details>
}