package co.touchlab.test;

import android.test.ActivityInstrumentationTestCase2;
import co.touchlab.robotiumtutorial.tictacro.TicTacRoActivity;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: matthewdavis
 * Date: 1/17/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacRoPlayer extends ActivityInstrumentationTestCase2<TicTacRoActivity>
{
    private Solo solo;

    public TicTacRoPlayer()
    {
        super(TicTacRoActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testTicTacRo() throws Exception
    {
        solo.assertCurrentActivity("Wrong Activity", TicTacRoActivity.class);
        solo.sleep(10000);
    }
}
