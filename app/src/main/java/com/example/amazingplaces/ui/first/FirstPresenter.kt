package com.example.amazingplaces.ui.first

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.amazingplaces.R
import com.example.amazingplaces.api.ApiServiceImpl
import com.example.amazingplaces.model.place_model.PlacesModel
import com.example.amazingplaces.utils.DistanceUtil
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class FirstPresenter: MvpPresenter<FirstView>() {
    private val apiService = ApiServiceImpl()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * It checks if the device has an internet connection, if it does, it gets the list of places from
     * the API and passes it to the view
     *
     * @param context Context - context of the application
     */
    fun startInitialization(context: Context){

        context.let {
            if (!apiService.hasPing(context)){
                viewState.onConnectionAbsence()
            }
        }
        compositeDisposable.add(
            apiService.getPlace()
                .subscribe({place ->
                    viewState.onListSetup(place)
                },
                    {
                        viewState.onRequestError(R.string.error)
                    })
        )
    }

    /**
     * It disposes the compositeDisposable object.
     */
    fun dispose(){
        compositeDisposable.dispose()
    }

}