package co.touchlab.test;

import android.support.v4.view.ViewPager;
import android.util.Log;
import co.touchlab.robotiumtutorial.R;

import android.view.View;
import android.widget.*;

import android.test.ActivityInstrumentationTestCase2;
import co.touchlab.robotiumtutorial.*;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;

import java.util.ArrayList;

/**
 * A suite of tests which use Robotium to test the RobotiumTestProject
 */
public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity>
{
    private Solo solo;

    public HomeActivityTest()
    {
        super(HomeActivity.class);
    }

    /*
     * Before each test, the Activity Under Test is re-launched, in this case HomeActivity
     * setUp() gets called next, before each test begins.
     */
    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());

        /*
         * Activities can be referenced using a String of their name, as is done here,
         *      or by using a Class object, seen in the test methods below
         *
         * The optional third argument to assertCurrentActivity() will further assert
         *      that this is a new instance of the Activity
         */
        //Sanity Check - Ensure we're in the HomeActivity to start
        solo.assertCurrentActivity("Not in HomeActivity", "HomeActivity", true);
    }

    public void testTextViewActivity() throws Exception
    {
        //Click the button to start the TextViewActivity
        solo.clickOnButton("Test TextView");
        //Ensure the button click brought us to the new activity
        solo.assertCurrentActivity("Not in TextViewActivity", TextViewActivity.class, true);

        /*
         * There are many ways to click Buttons using Robotium
         * First, we'll use the Text label on the button, in this case "hello", to reference it
         */
        //Click the hello button and ensure the text view is populated
        solo.clickOnButton("hello");
        //Ensure the text view was populated with the text, the button's logic converts it to caps to make the String unique
        Assert.assertTrue(solo.searchText("HELLO"));

        //Long press on the TextView to clear it
        //The clickOnText() family of methods can click any widget containing a matching string, not just Buttons
        solo.clickLongOnText("HELLO");
        //Ensure the TextView was cleared properly
        Assert.assertFalse(solo.searchText("HELLO"));

        /*
         * Next, we can get a list of all of a certain type of View, and reference them by index number from this list
         * The getCurrent{View}s method will return an ArrayList of all instances of {View}
         * There is also a getViews() method, which can be called with no arguments to get an ArrayList of all an Activity's Views
         */
        ArrayList<Button> buttonArrayList = solo.getCurrentButtons();
        //Loop through the ArrayList, finding the Button labeled with the String "world"
        Integer index = null;
        for(int i=0; i<buttonArrayList.size(); i++)
        {
            Button button = buttonArrayList.get(i);
            if("world".equals(button.getText()))
            {
                index = i;
                break;
            }
        }
        //Make sure we found the button
        Assert.assertNotNull(index);

        //The clickOnButton() method can take a String with the label of the button, as shown above,
        //      or the index of the Button in the getCurrentButtons() ArrayList
        solo.clickOnButton(index);
        //Ensure the TextView was populated
        Assert.assertTrue(solo.searchText("WORLD"));

        //Long press on the TextView to clear it
        solo.clickLongOnText("WORLD");
        //Ensure the TextView was cleared
        Assert.assertFalse(solo.searchText("WORLD"));

        /*
         * Finally, if we have access to the application source code,
         * we can use the Resource id from the R file to get a View object
         */
        //Get the "Hello World!" Button by referencing it's id
        Button helloWorldButton = (Button)solo.getView(R.id.button_hello_world);
        //Click on the Hello World! button and ensure the text view is populated again
        solo.clickOnView(helloWorldButton);
        TextView textView = (TextView)solo.getView(R.id.display_button_string_text_view);
        Assert.assertEquals("HELLO WORLD!", textView.getText().toString());

        //Long press on the TextView to clear it, and ensure it is cleared
        solo.clickLongOnText("HELLO WORLD!");
        Assert.assertFalse(solo.searchText("HELLO WORLD!"));
    }

    public void testEditTextActivity() throws Exception
    {
        //Click the button to start the EditTextActivity
        solo.clickOnButton("Test EditText");
        //Ensure the button click brought us to the new activity
        solo.assertCurrentActivity("Not in EditTextActivity", EditTextActivity.class, true);

        /*
         * For the enterText() and clearEditText() methods, the first parameter is the index of the EditText
         * When only one of a type of View appears in the Activity, we know it has an index of 0
         * Else, see testTextViewActivity for an example of determining a view's index using the getCurrent{View}s() methods
         */

        //Enter a non-matching string into the EditText
        solo.enterText(0, "android");
        //Click to perform String comparison
        solo.clickOnButton("Compare Text to \"Android\"");
        //Assert that the text we input did not match
        Assert.assertTrue(solo.searchText("FALSE"));

        //Clear the EditText
        solo.clearEditText(0);

        /*
         * enterText() and clearEditText() can also take an EditText object as the first argument
         *
         * The method typeText() has the same signatures as enterText() and serves almost the same function
         * The difference is that enterText() adds the entire String to the EditText at the same time,
         *    whereas typeText() will input characters one at a time.
         */

        //Get the editText by referencing it's id
        EditText editText = (EditText)solo.getView(R.id.comparison_edit_text);

        //Enter a matching string into the EditText, and perform the comparison
        solo.typeText(editText, "Android");
        solo.clickOnButton("Compare Text to \"Android\"");
        Assert.assertTrue(solo.searchText("TRUE"));

        //Clear the EditText
        solo.clearEditText(editText);
    }

    public void testListViewToggleButtonActivity() throws Exception
    {
        //Click the button to start the ListViewToggleButtonActivity
        solo.clickOnButton("Test Toggle Button and List View");
        //Ensure the button click brought us to the new activity
        solo.assertCurrentActivity("Not in ListViewToggleButtonActivity", ListViewToggleButtonActivity.class, true);

        //Get the ListView using the getView method
        //getView() can be used to find any subclass of View by resource id
        ListView listView = (ListView)solo.getView(R.id.toggled_list_view);
        //Ensure the View is not visible
        Assert.assertTrue(listView.getVisibility() != View.VISIBLE);

        /*
         * The isToggleButtonChecked() method can be used to determine if a particular button is checked
         * The parameter for this method can be either the index of the button or a String, similar to the clickOnButton() method
         */
        //Toggle Button starts Unchecked, ensure this is true
        Assert.assertFalse(solo.isToggleButtonChecked(0));

        //Check the Toggle Button, which will make the ListView Visible
        solo.clickOnToggleButton("Show List");
        Assert.assertTrue(solo.isToggleButtonChecked(0));
        Assert.assertEquals(listView.getVisibility(), View.VISIBLE);

        /*
         * ListViews, like the other widgets we've seen, can be referenced directly or by index number
         * The following lines of code will scroll up and down the list, to show the different ways this action can be performed
         * The sleep() method allows us to pause Robotium during execution.  I use them here to differentiate between each scroll
         */
        //Scroll the list
        solo.scrollDownList(listView);
        solo.sleep(2000);
        solo.scrollDownList(0);
        solo.sleep(2000);
        solo.scrollUpList(listView);
        solo.sleep(2000);
        solo.scrollUpList(0);
        solo.sleep(2000);

        //Scroll to a specific line
        solo.scrollListToLine(listView, 3);
        solo.sleep(2000);
        solo.scrollListToLine(0, 10);
        solo.sleep(2000);
        solo.scrollListToLine(listView, 5);
        solo.sleep(2000);

        //Scroll the list directly to the top or bottom
        solo.scrollListToTop(listView);
        solo.sleep(2000);
        solo.scrollListToBottom(0);
        solo.sleep(2000);

        //Click on "Zebra Cake!"
        solo.clickOnText("Zebra Cake!");
        //The searchText has an optional second parameter for the minimum number of appearances for the search string
        //Here, we assert that the clicked-on item appears twice, in the list and in the text view
        Assert.assertTrue(solo.searchText("Zebra Cake!", 2));

        /*
         * The clickInList() method can also be used to click on a particular item in a ListView
         * This method may be preferable because it returns a list of all TextViews
         *  contained within the selected row of the List
         * However the clickInList() method is relative to current position and 1-indexed,
         *  Views that are not in the visible portion of the list cannot be clicked because
         *  they have been recycled by the system and no longer exist as View objects
         */
        //Go back to the top of the list
        solo.scrollListToTop(listView);
        ArrayList<TextView> textViewArrayList;
        //Click the 7th visible item in the list, getting an ArrayList of TextViews contained within that item
        textViewArrayList = solo.clickInList(7);
        //Assert that we've clicked on the "Gingerbread" list item
        Assert.assertEquals(textViewArrayList.get(0).getText().toString(), "Gingerbread");

        //Scroll the list down slightly
        solo.scrollListToLine(listView, 2);
        //Click on the 7th item again. Because this only captures visible items...
        textViewArrayList = solo.clickInList(7);
        //This time, Gingerbread has not been clicked.
        Assert.assertFalse("Gingerbread".equals(textViewArrayList.get(0).getText().toString()));

        //Scroll back to the top of the list
        solo.scrollListToTop(listView);
        //Hide the List by clicking the toggle button
        solo.clickOnToggleButton("Hide List");

        //The search text method can also be used to find Text in Views that are not currently visible
        //Note, this is different than the above clickInList() example because
        // the "Cupcake" View is not recycled, it had a change in visibility
        Assert.assertTrue(solo.searchText("Cupcake"));
    }

    /*
     *
     */
    public void testPagerFragmentsPickersRadioActivity() throws Exception
    {
        //Click the button to start the DialogMenuWaitActivity
        solo.clickOnButton("Test View Pager, Fragments, Date/Time, Radio Buttons");
        //Ensure the button click brought us to the new activity
        solo.assertCurrentActivity("Not in PagerFragmentsPickersRadioActivity", PagerFragmentsPickersRadioActivity.class, true);

        /*
         * DatePicker and TimePicker methods, like those corresponding to EditTexts,
         *    can act on a view referenced either by index number or directly with a View object.
         * There are only one in each activity, so I'll reference them using the index 0 here
         * See the TextView and EditText test methods for getting a View object.
         */

        /*
         *  The setDatePicker() method has four parameters:
         *      int index/DatePicker datePicker - indicates which DatePicker to modify
         *      int year - the year to set
         *      int monthOfYear - the month to set (January == 0 - December == 11)
         *      int dayOfMonth - the day to set ( 1 - 31 )
         *  Note: choosing a month or day greater than allowed will cause it to wrap around to the next month/year
         *        and will NOT cause an error.
         */
        //Set the date to March 13, 1997
        solo.setDatePicker(0, 1997, 2, 13);
        //Ensure the date was selected correctly
        Assert.assertTrue(solo.searchText("Mar 13"));

        /*
         * The Two methods we can use for scrolling the ViewPager horizontally are scrollToSide() and scrollViewToSide()
         *  Scroll to side takes Solo.LEFT or Solo.RIGHT as it's only argument, depending on which way we want to scroll.
         *  Scroll view to side takes the View to scroll as the first argument, and Solo.LEFT or Solo.RIGHT as the second
         */
        //Switch to the next Fragment
        solo.scrollToSide(Solo.RIGHT);

        /*
         * There are two ways we can wait for Fragments with Robotium, the
         *      waitForFragmentById() and waitForFragmentByTag() methods
         *
         * This part is in progress, not easy to set fragment id or tag dynamically
         */
        //Wait for the new Fragment
        //solo.waitForFragmentById(2222);

        /*
         *  The setTimePicker() method has three parameters:
         *      int index/TimePicker timePicker - indicates which TimePicker to modify
         *      int hour - 24 hour clock: 0 For midnight, 1-11 for AM, 12 for noon, 13-23 For PM
         *          - choosing an hour greater than 23 will display (selection % 12 PM)
         *      int minute - 0-59
         *          - choosing a minute greater than 59 will display (selection % 60) and will NOT increment the hour accordingly
         */
        //Set the time to 3:52 AM
        solo.setTimePicker(0, 3, 52);
        //Ensure time was set correctly
        Assert.assertTrue(solo.searchText("3:52"));

        //Set the time to 3:52 PM
        solo.setTimePicker(0, 15, 52);
        //Ensure the time was set correctly again
        Assert.assertTrue(solo.searchText("15:52"));

        //Grab a reference to the ViewPager and scroll it horizontally
        solo.scrollViewToSide(solo.getView(ViewPager.class, 0), Solo.RIGHT);

        //Wait for the new Fragment - for both waitForFragment methods, we can optionally add a timeout
        //solo.waitForFragmentByTag("DAY_FRAGMENT", 10000);

        //Click on the RadioButton for Tuesday
        //The clickOnRadioButton() method can only be used with the index of the Button
        solo.clickOnRadioButton(2);
        //Ensure the TextView contains the text "Tuesday"
        Assert.assertTrue(solo.searchText("Tuesday", 2));

        //Click on the RadioButton for Thursday
        solo.clickOnRadioButton(4);
        //We can also use the isRadioButtonChecked() method to assert this
        Assert.assertTrue(solo.isRadioButtonChecked(4));

        //Click on the RadioButton for Saturday
        solo.clickOnRadioButton(6);
        //Alternatively, isRadioButtonChecked() can take the text on the button as an argument
        Assert.assertTrue(solo.isRadioButtonChecked("Saturday"));
    }

    public void testDialogMenuWaitActivity() throws Exception
    {
        //Click the button to start the DialogMenuWaitActivity
        solo.clickOnButton("Test Menu and Wait");
        //Ensure the button click brought us to the new activity
        solo.assertCurrentActivity("Not in DialogMenuWaitActivity", DialogMenuWaitActivity.class, true);

        //Click the first menu item, to display text
        solo.clickOnMenuItem("Show Text");
        //Default Timeout for searchText() is 5 seconds.
        Assert.assertTrue(solo.searchText("Still Waiting..."));
        //We can use waitForText to set an arbitrary timeout, in this case, 10 seconds
        //The second parameter for waitForText is the minimum number of instances we're searching for
        Assert.assertTrue(solo.waitForText("Done!", 1, 10000));

        //The menu can also be opened without immediately selecting an option by using sendKey()
        solo.sendKey(Solo.MENU);
        //Click the second menu item, to display an image
        solo.clickOnText("Show Images");
        //Wait for the images to appear.  The second parameter says we're waiting for a minimum of 5 WrappedImageViews.
        Assert.assertTrue(solo.waitForView(WrappedImageView.class, 5, 10000));

        //Click the third menu item, to display a dialog
        solo.clickOnMenuItem("Show Dialog");
        //Cancel the dialog
        solo.clickOnText("Cancel");
        //Wait for the Dialog to close
        Assert.assertTrue(solo.waitForDialogToClose(10000));

        //Click the final menu item, to launch a new Activity
        solo.clickOnMenuItem("Start Activity");
        //Check to make sure it starts
        Assert.assertTrue(solo.waitForActivity("DummyActivity", 10000));
        //Go back
        solo.goBack();
        //The optional third argument of assertCurrentActivity() ensures it is a new instance, it can be omitted in this case
        solo.assertCurrentActivity("Not a new instance of DialogMenuWaitActivity", DialogMenuWaitActivity.class);
    }

    /*
     * Boilerplate tearDown() code.
     * tearDown() is called upon the completion of each test
     */
    @Override
    public void tearDown() throws Exception
    {
        //Close all activities
        solo.finishOpenedActivities();

        try {
            solo.finalize();
        } catch (Throwable e) {
            //Eat this for now... not sure of the types of things that get thrown
        }
        getActivity().finish();
        super.tearDown();
    }
}
