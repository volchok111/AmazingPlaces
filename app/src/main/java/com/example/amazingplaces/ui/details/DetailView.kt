package com.example.amazingplaces.ui.details

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.amazingplaces.model.detail_model.Details
import com.example.amazingplaces.ui.home.HomeView

/* A Moxy annotation. It is used to define the state strategy. */
@StateStrategyType(SingleStateStrategy::class)
/* The DetailView interface extends the HomeView interface and adds three new methods */
interface DetailView : HomeView{
    fun onLoading()
    fun onError()
    fun onLoaded(details: Details)
}