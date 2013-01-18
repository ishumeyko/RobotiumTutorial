package co.touchlab.robotiumtutorial;

import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * An Activity whose root layout is a ViewPager.
 * The ViewPager contains three Fragments which contain a DatePicker, TimePicker, and RadioGroup, respectively
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

        private int TIME_FRAGMENT_ID = 2222;
        private String DAY_FRAGMENT_TAG = "DAY_FRAGMENT";

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

    private abstract class SimpleFragment extends Fragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.date_fragment, null);

            FrameLayout frameLayout = (FrameLayout)linearLayout.findViewById(R.id.fragment_frame);
            final TextView textView = (TextView)linearLayout.findViewById(R.id.fragment_text_view);

            frameLayout.addView(initSimpleFragmentBody(textView));

            return linearLayout;
        }

        protected abstract View initSimpleFragmentBody(TextView textView);
    }

    private class DateFragment extends SimpleFragment
    {
        @Override
        protected View initSimpleFragmentBody(final TextView textView)
        {
            DatePicker datePicker = new DatePicker(PagerFragmentsPickersRadioActivity.this);
            datePicker.init(2000, 0, 1, new DatePicker.OnDateChangedListener()
            {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                {
                    textView.setText(new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime().toString());
                }
            });
            return datePicker;
        }
    }

    private class TimeFragment extends SimpleFragment
    {
        @Override
        protected View initSimpleFragmentBody(final TextView textView)
        {
            TimePicker timePicker = new TimePicker(PagerFragmentsPickersRadioActivity.this);
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
            {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
                {
                    StringBuilder timeBuilder = new StringBuilder();
                    timeBuilder.append(hourOfDay).append(":");

                    if(minute > 9)
                    {
                        timeBuilder.append(minute);
                    }
                    else
                    {
                        timeBuilder.append("0").append(minute);
                    }

                    textView.setText(timeBuilder.toString());
                }
            });
            return timePicker;
        }
    }

    private class DayFragment extends SimpleFragment
    {
        @Override
        protected View initSimpleFragmentBody(final TextView textView)
        {
            RadioGroup radioGroup = (RadioGroup)getLayoutInflater(null).inflate(R.layout.radio_group_days, null);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    String selectedDay;

                    switch (checkedId)
                    {
                        case R.id.sunday_button:
                            selectedDay = getString(R.string.sunday);
                            break;
                        case R.id.monday_button:
                            selectedDay = getString(R.string.monday);
                            break;
                        case R.id.tuesday_button:
                            selectedDay = getString(R.string.tuesday);
                            break;
                        case R.id.wednesday_button:
                            selectedDay = getString(R.string.wednesday);
                            break;
                        case R.id.thursday_button:
                            selectedDay = getString(R.string.thursday);
                            break;
                        case R.id.friday_button:
                            selectedDay = getString(R.string.friday);
                            break;
                        case R.id.saturday_button:
                            selectedDay = getString(R.string.saturday);
                            break;
                        default:
                            selectedDay = "";
                    }

                    textView.setText(selectedDay);
                }
            });
            return radioGroup;
        }
    }
}
