package co.touchlab.robotiumtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void testTextView(View v)
    {
        startActivity(new Intent(HomeActivity.this, TextViewActivity.class));
    }

    public void testEditText(View v)
    {
        startActivity(new Intent(HomeActivity.this, EditTextActivity.class));
    }

    public void testToggleListView(View v)
    {
        startActivity(new Intent(HomeActivity.this, ListViewToggleButtonActivity.class));
    }

    public void testPagerFragmentsDates(View v)
    {
        startActivity(new Intent(HomeActivity.this, PagerFragmentsPickersRadioActivity.class));
    }

    public void testMenuWait(View v)
    {
        startActivity(new Intent(HomeActivity.this, DialogMenuWaitActivity.class));
    }
}
