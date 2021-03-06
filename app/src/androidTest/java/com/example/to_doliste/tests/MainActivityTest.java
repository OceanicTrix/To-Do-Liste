package com.example.to_doliste.tests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.to_doliste.activities.MainActivity;
import com.example.to_doliste.R;
import com.example.to_doliste.room.AufgabeRoomDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    AufgabeRoomDatabase db = null;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void klickAufAufgabe() {
        db = AufgabeRoomDatabase.getInstance(mActivityRule.getActivity());
        onView(ViewMatchers.withId(R.id.main_Create)).perform(click());
        onView(withId(R.id.create_etTitel)).perform(typeText("Aufgabe"));
        onView(withId(R.id.create_etDatum)).perform(typeText("21.12.2021"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.create_btnSpeichern)).perform(click());
        onData(anything())
                .inAdapterView(withId(R.id.auftraege))
                .atPosition(0)
                .perform(click());
        onView(withText("Aufgabe")).check(matches(isDisplayed()));
        onView(withText("21.12.2021")).check(matches(isDisplayed()));
        db.clearAllTables();
    }

    @Test
    public void klickAufFloatButton(){
        onView(withId(R.id.main_Create)).perform(click());
        onView(withId(R.id.create_btnSpeichern)).check(matches(isDisplayed()));
    }
}