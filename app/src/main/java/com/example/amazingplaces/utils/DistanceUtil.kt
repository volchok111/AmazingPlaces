package com.example.amazingplaces.utils

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

object DistanceUtil {
    private const val COMPANY_LATITUDE = 50.100558
    private const val COMPANY_LONGITUDE = 14.424798

    /**
     * It calculates the distance between the company's location and the location of the user
     *
     * @param lat latitude of the user
     * @param lon Longitude of the user
     * @return The distance in kilometers between the company and the user.
     */
    fun distance( lat: Double, lon: Double): Int {
        val theta = COMPANY_LONGITUDE - lon
        var dist = (sin(deg2rad(COMPANY_LATITUDE))
                * sin(deg2rad(lat))
                + (cos(deg2rad(COMPANY_LATITUDE))
                * cos(deg2rad(lat))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        dist *= 1.60934
        return dist.roundToInt()
    }

    /**
     * It converts degrees to radians
     *
     * @param deg The degree value to be converted to radians.
     * @return The distance between two points on the Earth.
     */
    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    /**
     * It converts radians to degrees
     *
     * @param rad The angle in radians.
     * @return The distance between two points on the Earth.
     */
    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}