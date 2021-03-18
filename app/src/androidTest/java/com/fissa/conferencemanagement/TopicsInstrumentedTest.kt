package com.fissa.conferencemanagement

import android.widget.ImageView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.fissa.conferencemanagement.view.MainActivity
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
class TopicsInstrumentedTest {
    @Rule @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Before
    fun setUp(){
        onView(withId(R.id.topics)).perform(click())
    }

    @Test
    fun addingTopic(){
        val correctTopic = "Testing with espresso2 30min"
        val onlyNameTopic = "Testing with espresso2"
        onView(withId(R.id.txtFld_newTopic)).perform(typeText(correctTopic))
        onView(withId(R.id.imgVw_addTopic)).perform(click())
        onData(withSubstring(onlyNameTopic)).inAdapterView(withId(R.id.listView))//.perform(scrollTo())
    }

    @Test
    fun removeTopic(){
        val correctTopic = "Testing remove 30min"
        onView(withId(R.id.txtFld_newTopic)).perform(typeText(correctTopic))
        onView(withId(R.id.imgVw_addTopic)).perform(click())
        val rowItem = onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0)
        rowItem.perform(scrollTo())
        rowItem.onChildView(instanceOf(ImageView::class.java)).perform(click())
        onView(withText(R.string.success_removed_topic)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun addingWrongTopic(){
        val wrongTopic = "Testing with espresso"
        onView(withId(R.id.txtFld_newTopic)).perform(typeText(wrongTopic))
        onView(withId(R.id.imgVw_addTopic)).perform(click())
        onView(withText(R.string.format_event_error)).check(ViewAssertions.matches(isDisplayed()))
    }
}