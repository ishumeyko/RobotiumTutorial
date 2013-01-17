package co.touchlab.robotiumtutorial;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity that does nothing.  Used by DialogMenuWaitActivity's Test to give an example of waiting for Activities.
 */
public class DummyActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy);
    }
}
