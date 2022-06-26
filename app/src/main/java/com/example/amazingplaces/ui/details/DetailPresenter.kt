package com.example.amazingplaces.ui.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.amazingplaces.api.ApiServiceImpl
import com.example.amazingplaces.utils.DistanceUtil
import io.reactivex.Flowable
import kotlin.math.roundToInt
import kotlin.math.sin


@InjectViewState
class DetailPresenter : MvpPresenter<DetailView>() {
    private val apiService = ApiServiceImpl()

    /**
     * We're calling the `getPlaceById` function from the `ApiService` class, and passing in the `id`
     * parameter. If the call is successful, we're calling the `onLoaded` function from the `viewState`
     * object, and passing in the response. If the call is unsuccessful, we're calling the `onError`
     * function from the `viewState` object
     *
     * @param id The id of the place you want to get information about.
     */
    fun getInfo(id: String) {
        viewState.onLoading()
        val disposable = apiService.getPlaceById(id)
            .subscribe({
                viewState.onLoaded(it)
            }, {
                viewState.onError()
            })
    }
}