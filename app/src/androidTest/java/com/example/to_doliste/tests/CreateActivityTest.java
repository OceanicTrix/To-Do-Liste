package com.example.to_doliste.tests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.to_doliste.activities.CreateActivity;
import com.example.to_doliste.R;
import com.example.to_doliste.room.AufgabeRoomDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CreateActivityTest {
    AufgabeRoomDatabase db = null;
    @Rule
    public ActivityTestRule<CreateActivity> cActivityRule = new ActivityTestRule<>(CreateActivity.class);

    @Before
    public void runBeforeEveryTest() {
        db = AufgabeRoomDatabase.getInstance(cActivityRule.getActivity());
    }

    @Test
    public void testNeueAufgabe() {
        onView(ViewMatchers.withId(R.id.create_etTitel)).perform(typeText("Aufgabe"));
        onView(withId(R.id.create_etDatum)).perform(typeText("21.12.2021"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.create_btnSpeichern)).perform(click());
        onView(withText("Aufgabe\n21.12.2021")).check(matches(isDisplayed()));
        db.clearAllTables();
    }

    @Test
    public void testFehlerLeer() {
        onView(withId(R.id.create_btnSpeichern)).perform(click());
        onView(withText("Fehler")).check(matches(isDisplayed()));
        db.clearAllTables();
    }
}