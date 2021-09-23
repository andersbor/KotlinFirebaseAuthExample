package dk.easj.anbo.firebaseauthexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dk.easj.anbo.firebaseauthexample", appContext.packageName)

        onView(withText("Authentication")).check(matches(isDisplayed()));
        onView(withId(R.id.emailInputField))
            .perform(clearText())
            .perform(typeText("anbo@secret6.dk"));
        onView(withId(R.id.passwordInputField))
            .perform(clearText())
            .perform(typeText("secret6"))
            .perform(closeSoftKeyboard());
        onView(withId(R.id.sign_in)).perform(click());
        pause(1000); // to wait for Firebase response to arrive
        onView(withId(R.id.textview_second)).check(matches(withText(("Welcome anbo@secret6.dk"))));
    }

    private fun pause(millis: Long) {
        try {
            Thread.sleep(millis)
            // https://www.repeato.app/android-espresso-why-to-avoid-thread-sleep/
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}