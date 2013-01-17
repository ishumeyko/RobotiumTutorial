package co.touchlab.robotiumtutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

/**
 * Created with IntelliJ IDEA.
 * User: matthewdavis
 * Date: 1/17/13
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class PagerFragmentsPickersRadioActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_fragments_dates_radio_activity);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new DatesAndTimesFragmentPagerAdapter(getSupportFragmentManager()));
    }

    private class DatesAndTimesFragmentPagerAdapter extends FragmentPagerAdapter
    {
        Fragment[] fragments = { new DateFragment(), new TimeFragment(), new DayFragment() };

        public DatesAndTimesFragmentPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int i)
        {
            return fragments[i];
        }

        @Override
        public int getCount()
        {
            return fragments.length;
        }
    }

    private class DateFragment extends Fragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            DatePicker datePicker = new DatePicker(PagerFragmentsPickersRadioActivity.this);
            return datePicker;
        }
    }

    private class TimeFragment extends Fragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            TimePicker timePicker = new TimePicker(PagerFragmentsPickersRadioActivity.this);
            return timePicker;
        }
    }

    private class DayFragment extends Fragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            LinearLayout radioGroup = (LinearLayout)getLayoutInflater(null).inflate(R.layout.radio_group_days, null);
            return radioGroup;
        }
    }
}
