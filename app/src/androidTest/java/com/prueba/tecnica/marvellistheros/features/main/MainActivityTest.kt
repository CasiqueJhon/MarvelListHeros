package com.prueba.tecnica.marvellistheros.features.main

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prueba.tecnica.marvellistheros.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun launchMainActivityAndVerifyUI() {
        launchActivity<MainActivity>()

        Espresso.onView(withId(R.id.navHostFragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}