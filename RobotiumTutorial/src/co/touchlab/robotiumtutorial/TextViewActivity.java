package co.touchlab.robotiumtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This Activity contains a TextView and Buttons.
 * Clicking on a button populates the TextView with its label
 */
public class TextViewActivity extends Activity
{
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view_activity);
        textView = (TextView)findViewById(R.id.display_button_string_text_view);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((TextView)v).setText("");
                return true;
            }
        });
    }

    public void buttonClicked(View button)
    {
        textView.setText(((Button)button).getText().toString().toUpperCase());
    }
}
