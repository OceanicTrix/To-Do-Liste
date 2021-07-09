package com.example.to_doliste.tests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.to_doliste.activities.MainActivity;
import com.example.to_doliste.R;
import com.example.to_doliste.room.AufgabeRoomDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


public class UpdateActitivityTest {
    AufgabeRoomDatabase db = null;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void runBeforeEveryTest(){
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
    }
    @Test
    public void testVerwerfen(){
        onView(withId(R.id.update_btnVerwerfen)).perform(click());
        onView(withText("Aufgabe\n21.12.2021")).check(matches(isDisplayed()));
        db.clearAllTables();
    }
    @Test
    public void testLoeschen(){
        onView(withId(R.id.update_btnLoeschen)).perform(click());
        onView(withText("Aufgabe\n21.12.2021")).check(doesNotExist());
        db.clearAllTables();
    }
    @Test
    public void testAendern(){
        onView(withId(R.id.update_etTitel)).perform(clearText());
        onView(withId(R.id.update_etTitel)).perform(typeText("AufgabeNeu"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.update_btnSpeichern)).perform(click());
        onView(withText("AufgabeNeu\n21.12.2021")).check(matches(isDisplayed()));
        db.clearAllTables();
    }
}
