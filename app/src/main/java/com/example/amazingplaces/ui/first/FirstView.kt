package com.example.amazingplaces.ui.first

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.amazingplaces.model.place_model.PlacesModel
import com.example.amazingplaces.ui.home.HomeView

/* A Moxy annotation. It is used to define the state strategy. */
@StateStrategyType(SingleStateStrategy::class)

/* Defining the interface for the FirstView. */
interface FirstView: HomeView {
    fun onListSetup(places:  List<PlacesModel>)
    fun addPlace(place: PlacesModel)
}