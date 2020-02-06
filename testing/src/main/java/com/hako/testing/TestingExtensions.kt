package com.hako.testing

import androidx.annotation.RestrictTo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.isTextDisplayed() = onView(withText(this)).check(matches(ViewMatchers.isDisplayed()))