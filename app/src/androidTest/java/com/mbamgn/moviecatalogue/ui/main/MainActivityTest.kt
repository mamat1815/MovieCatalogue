package com.mbamgn.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.utils.EspressoResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoResource.espressoIdlingResource())

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoResource.espressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed())).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun getFavMovie() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.tg_fav)).perform(click())
        pressBack()
        onView(withId(R.id.fab_fav)).perform(click())
        onView(allOf(withId(R.id.rv_fav_movie), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.bg_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tag)).check(matches(isDisplayed()))
        onView(withId(R.id.percent_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tg_fav)).perform(click())
    }

    @Test
    fun loadDetailMovie() {
        onView(allOf(withId(R.id.rv_movie), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.bg_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tag)).check(matches(isDisplayed()))
        onView(withId(R.id.percent_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(allOf(withId(R.id.rv_tv_show), isDisplayed())).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(allOf(withId(R.id.rv_tv_show), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.bg_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tag)).check(matches(isDisplayed()))
        onView(withId(R.id.percent_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun getFavTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(allOf(withId(R.id.rv_tv_show), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.tg_fav)).perform(click())
        pressBack()
        onView(withId(R.id.fab_fav)).perform(click())
        onView(withText(R.string.tv_show)).perform(click())
        onView(allOf(withId(R.id.rv_fav_tv_show), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.bg_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_tag)).check(matches(isDisplayed()))
        onView(withId(R.id.percent_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tg_fav)).perform(click())
    }

}