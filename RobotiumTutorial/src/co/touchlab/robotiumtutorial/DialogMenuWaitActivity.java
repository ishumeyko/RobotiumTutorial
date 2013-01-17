package co.touchlab.robotiumtutorial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This activity contains a Menu with various options for displaying widgets and navigation
 * All menu options are performed after some delay, via a Handler, to test the Robotium "wait" methods
 */
public class DialogMenuWaitActivity extends Activity
{
    private TextView textView;
    private LinearLayout layoutRoot;

    private Handler delayHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_menu_wait_activity);

        textView = (TextView)findViewById(R.id.delayed_textview);
        layoutRoot = (LinearLayout)findViewById(R.id.layout_root);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void showText(MenuItem menuItem)
    {
        textView.setText("Waiting...");

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Still Waiting...");
            }
        }, 3000);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Done!");
            }
        }, 8000);
    }

    public void showImages(MenuItem menuItem)
    {
        for(int i = 1000; i < 6000; i=i+1000)
        {
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    WrappedImageView imageView = new WrappedImageView(DialogMenuWaitActivity.this);
                    imageView.setImageResource(android.R.drawable.sym_def_app_icon);
                    layoutRoot.addView(imageView);
                }
            }, i);
        }
    }

    public void showDialog(MenuItem menuItem)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DialogMenuWaitActivity.this);
        builder.setTitle("This is a Dialog");
        builder.setMessage("Press the button to close. This will take a few seconds.");

        //The default behavior of dialogs made with a Builder class are to close on any button click.
        //We're going to use a tiny hack to keep it open a little longer
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) { }
        });

        final AlertDialog dialog = builder.show();

        //Once the dialog is shown, we can get the button directly from the dialog and overwrite the onClick()
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 6000);
            }
        });
    }

    public void startActivity(MenuItem menuItem)
    {
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(DialogMenuWaitActivity.this, DummyActivity.class));
            }
        }, 4000);
    }
}
