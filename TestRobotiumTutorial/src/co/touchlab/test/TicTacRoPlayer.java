package co.touchlab.test;

import android.test.ActivityInstrumentationTestCase2;
import co.touchlab.robotiumtutorial.tictacro.BoardTextView;
import co.touchlab.robotiumtutorial.tictacro.TicTacRoActivity;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;

import java.util.Random;

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

        for(int i=1; i<5; i++)
        {
            solo.waitForText("X", i, 100000);

            int moveSelection = new Random().nextInt(9);

            while(!solo.searchText("O", i))
            {
                solo.clickLongOnView(solo.getView(BoardTextView.class, moveSelection));
                moveSelection = (moveSelection + 1)  % 9;
            }
        }
    }
}
