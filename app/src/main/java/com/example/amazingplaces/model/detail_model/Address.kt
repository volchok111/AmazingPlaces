package com.example.amazingplaces.model.detail_model

/**
 * An Address is a data class with four properties: city, country, street, and zip.
 * @property {String} city - The city of the address.
 * @property {String} country - The country where the user lives.
 * @property {String} street - The street name and number.
 * @property {String} zip - The zip code of the address.
 */
data class Address(
    val city: String,
    val country: String,
    val street: String,
    val zip: String
)