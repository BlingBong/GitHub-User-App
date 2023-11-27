package com.example.githubuser.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.githubuser.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyQuery = "BlingBong"
    private val dummyName = "Rivano Ardiyan Taufiq Kurniawan"

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testSearchFavorite() {
        onView(withId(R.id.search_view)).perform(click())
        onView(withId(R.id.search_src_text)).perform(typeText(dummyQuery), closeSoftKeyboard())

        onView(withId(R.id.rv_user)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_user)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(R.id.tv_name_received)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name_received)).check(matches(withText(dummyName)))

        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.menu_favorite)).perform(click())
    }
}