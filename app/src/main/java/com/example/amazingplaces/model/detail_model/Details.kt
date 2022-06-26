package com.example.amazingplaces.model.detail_model

/**
 * Details is a data class that has a bunch of properties, some of which are other data classes.
 * @property {Address} address - The address of the place.
 * @property {String} description - A description of the place.
 * @property {String} image - The URL of the image of the place.
 * @property {Location} location - This is the location of the place.
 * @property {String} name - The name of the place.
 * @property {String} thumbnail_image - The URL of the image to be displayed in the list.
 * @property {String} url - The URL of the restaurant's website.
 * @property {String} uuid - The unique identifier for the venue.
 * @property {String} distance - This is the distance between the user's current location and the
 * location of the venue.
 */
data class Details(
    val address: Address,
    val description: String,
    val image: String,
    val location: Location,
    val name: String,
    val thumbnail_image: String,
    val url: String,
    val uuid: String,
    var distance: String
)