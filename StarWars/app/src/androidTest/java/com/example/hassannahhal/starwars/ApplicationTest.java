package com.example.hassannahhal.starwars;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.Random;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {


    private final static int MAX = 21;
    private final static int MIN = 1;
    private final static String SPINNER_CLICKED = "Spinner clicked";
    private final static String BUTTON_CLICKED = "Button clicked";
    private Solo solo;


    public ApplicationTest() {
        super(MainActivity.class);
    }


    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }


    public void sendTroopers() {
        solo.pressSpinnerItem(R.id.plantsSpinner, randInt());
        solo.assertCurrentActivity(SPINNER_CLICKED, MainActivity.class);

        solo.clickOnButton(R.id.button);
        solo.assertCurrentActivity(BUTTON_CLICKED, MainActivity.class);

    }


    public void testStuff() {
        while (true) {

            View spinner = solo.getView(R.id.plantsSpinner);

            solo.clickOnView(spinner);
            solo.clickOnView(solo.getView(TextView.class, randInt()));

            solo.clickOnView(solo.getView(R.id.button));
        }
    }

    public int randInt() {
        Random rand = new Random();
        int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;

        return randomNum;
    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}