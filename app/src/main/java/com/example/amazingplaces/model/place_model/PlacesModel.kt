package com.example.amazingplaces.model.place_model

import java.io.Serializable

/**
 * A PlacesModel is a data class that has a location, name, thumbnail_image, uuid, and distance.
 * @property {Location} location - This is the location of the place.
 * @property {String} name - The name of the place.
 * @property {String} thumbnail_image - The URL of the image to be displayed in the list.
 * @property {String} uuid - The unique identifier for the place.
 * @property {Int} distance - The distance between the user's current location and the place.
 */
data class PlacesModel(
    val location: Location,
    val name: String,
    val thumbnail_image: String,
    val uuid: String,
    var distance: Int
): Serializable {

    companion object {
        /* Creating a comparator object that compares the names of two places. */
        val sortByNameAz = object : Comparator<PlacesModel> {
            override fun compare(o1: PlacesModel?, o2: PlacesModel?): Int {
                if (o1 != null && o2 != null)
                    return o1.name.compareTo(o2.name)
                return 0
            }
        }

        /* Creating a comparator object that compares the names of two places. */
        val sortByNameZa = object : Comparator<PlacesModel> {
            override fun compare(o1: PlacesModel?, o2: PlacesModel?): Int {
                if (o1 != null && o2 != null)
                    return o2.name.compareTo(o1.name)
                return 0
            }
        }
    }
}