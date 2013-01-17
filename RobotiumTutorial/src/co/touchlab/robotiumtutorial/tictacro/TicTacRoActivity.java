package co.touchlab.robotiumtutorial.tictacro;

import android.app.Activity;
import android.os.Bundle;

import co.touchlab.robotiumtutorial.R;

/**
 * Created with IntelliJ IDEA.
 * User: matthewdavis
 * Date: 1/17/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacRoActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_ro);
    }
}
