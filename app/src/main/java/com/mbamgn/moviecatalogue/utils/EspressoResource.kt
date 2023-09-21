package com.mbamgn.moviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoResource {

    private const val RESOURCE = "GLOBAL"
    private val IdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = IdlingResource.increment()
    fun decrement() = IdlingResource.decrement()
    fun espressoIdlingResource(): IdlingResource = IdlingResource

}