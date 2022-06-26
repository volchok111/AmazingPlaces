package com.example.amazingplaces.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.amazingplaces.model.detail_model.Details
import com.example.amazingplaces.model.place_model.Places
import com.example.amazingplaces.model.place_model.PlacesModel
import com.example.amazingplaces.network.HttpRetrofit
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiServiceImpl: ApiService {
    private val httpRetrofit: HttpRetrofit = HttpRetrofit

    /**
     * It checks if the device has internet connectivity.
     *
     * @param context Context
     * @return Boolean
     */
    override fun hasPing(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = manager.activeNetwork ?: return false
        val actNw = manager.getNetworkCapabilities(nw)
        if (actNw != null) {
            return actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR)
        }
        return true
    }

    /**
     * It returns a Single<Places> object.
     *
     * @return Single<Places>
     */
    override fun getPlace(): Single<Places> {
        return httpRetrofit.getHttpApi().getPlaces()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * It gets the place by id.
     *
     * @param id The id of the place you want to get details for.
     * @return Single<Details>
     */
    override fun getPlaceById(id: String): Single<Details> {
        return httpRetrofit.getHttpApi().getPlaceById(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
