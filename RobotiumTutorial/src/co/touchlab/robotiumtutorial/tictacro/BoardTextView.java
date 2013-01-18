package co.touchlab.robotiumtutorial.tictacro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import co.touchlab.robotiumtutorial.R;

/**
 * A stub that extends TextView, used by Robotium so the TicTacRo board has unique views.
 */
public class BoardTextView extends TextView
{

    public BoardTextView(Context context)
    {
        super(context);
        //super(context, null, R.style.board_text_view_style);
    }

    public BoardTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //super(context, null, R.style.board_text_view_style);
    }

    public BoardTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs);
        //super(context, null, R.style.board_text_view_style);
    }
}
