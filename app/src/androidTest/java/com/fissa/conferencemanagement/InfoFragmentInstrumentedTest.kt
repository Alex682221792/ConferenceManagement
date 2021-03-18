package com.fissa.conferencemanagement

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fissa.conferencemanagement.view.info_app.InfoAppFragment
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoFragmentInstrumentedTest {
    @Test
    fun testDisplayInfo(){
        launchFragmentInContainer<InfoAppFragment>(
            initialState = Lifecycle.State.RESUMED
        )
        onView(withId(R.id.title_frg)).check(matches(withText(R.string.info_title) ))
        onView(withId(R.id.subtitle_frg_info)).check(matches(withText(R.string.info_subTitle) ))
        onView(withId(R.id.description_frg_ino)).check(matches(withText(containsString("You are planning"))))
    }
}