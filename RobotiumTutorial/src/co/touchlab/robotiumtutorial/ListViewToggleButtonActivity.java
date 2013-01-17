package co.touchlab.robotiumtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * This Activity contains a ToggleButton, TextView, and ListView
 * The ToggleButton controls the Visibility of the ListView
 * Clicking on an item in the ListView will populate the TextView with the item's String contents
 */
public class ListViewToggleButtonActivity extends Activity
{
    private ListView listView;
    private TextView textView;

    private final String[] listItems = {"1.0", "1.1", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb",
            "Ice Cream Sandwich", "Jellybean", "Key Lime Pie?", "L?", "M?", "N?", "O?", "P?", "Q?", "R?", "S?", "T?",
            "...", "Zebra Cake!"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_toggle_button_activity);

        listView = (ListView)findViewById(R.id.toggled_list_view);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));

        textView = (TextView)findViewById(R.id.text_view_display_list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText( ((TextView)view).getText().toString() );
            }
        });
    }

    public void toggleList(View toggleButton)
    {
        listView.setVisibility( ((ToggleButton)toggleButton).isChecked() ? View.VISIBLE : View.GONE );
    }
}
