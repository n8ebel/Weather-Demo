package com.goobar.io.premiseweather.location

data class Location(val zipcode: String) {
    override fun toString(): String {
        return zipcode
    }

    companion object {
        fun fromString(zipcode: String) = Location(zipcode)
    }
}