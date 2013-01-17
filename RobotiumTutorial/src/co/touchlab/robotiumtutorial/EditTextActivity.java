package co.touchlab.robotiumtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This Activity contains and EditText, Button, and TextView
 * Pressing the Button compares the String in the TextView with the String "Android"
 * The TextView will display TRUE or FALSE based on the result of the comparison
 */
public class EditTextActivity extends Activity
{
    private EditText editText;
    private TextView textView;

    private final String COMPARE_TO_STRING = "Android";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text_activity);
        editText = (EditText)findViewById(R.id.comparison_edit_text);
        textView = (TextView)findViewById(R.id.comparison_text_view);
    }

    public void performStringComparison(View v)
    {
        String result = "FALSE";

        if(COMPARE_TO_STRING.equals(editText.getText().toString()))
            result = "TRUE";

        textView.setText(result);
    }
}
