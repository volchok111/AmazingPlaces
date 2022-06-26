package com.example.amazingplaces.ui.home

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/* A Moxy annotation that tells the framework to use the `SingleStateStrategy` strategy. */
@StateStrategyType(SingleStateStrategy::class)
interface HomeView: MvpView {
    /**
     * A function that is called when a request is started.
     */
    fun onRequestStart()
    /**
     * `onRequestComplete` is a function that takes no arguments and returns nothing.
     */
    fun onRequestComplete()
    /**
     * `onRequestError` is a function that takes an integer and returns nothing.
     *
     * @param message The message to be displayed in the snackbar.
     */
    fun onRequestError(message: Int)
    /**
     * A function that is called when the connection is lost.
     */
    fun onConnectionAbsence()
}