package com.example.to_doliste;

import androidx.test.rule.ActivityTestRule;

import com.example.to_doliste.room.AufgabeData;
import com.example.to_doliste.room.AufgabeRoomDatabase;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.collection.IsEmptyIterable;
import org.hamcrest.core.Is;
import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

public class UpdateActitivityTest {
    AufgabeRoomDatabase db = null;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void runBeforeEveryTest(){
        db = AufgabeRoomDatabase.getInstance(mActivityRule.getActivity());
        if (db.getRoomDao().getAll().size() != 0){
            db.clearAllTables();
        }
        AufgabeData data = new AufgabeData("Aufgabe","21.12.2021", ":)");
        db.getRoomDao().insert(data);
        onData(anything())
                .inAdapterView(withId(R.id.auftraege))
                .atPosition(0)
                .perform(click());
    }
    @Test
    public void testVerwerfen(){
        onView(withId(R.id.update_btnVerwerfen)).perform(click());
        onView(withText("Aufgabe\n21.12.2021")).check(matches(isDisplayed()));
    }
    @Test
    public void testLoeschen(){
        try{
            Thread.sleep(2000);
        }
        catch (Exception e){

        }
        onView(withId(R.id.update_btnLoeschen)).perform(click());
        try{
            Thread.sleep(1000000);
        }
        catch (Exception e){

        }
        onView(withText("Aufgabe\n21.12.2021")).check((matches(not(isDisplayed()))));
    }
}
