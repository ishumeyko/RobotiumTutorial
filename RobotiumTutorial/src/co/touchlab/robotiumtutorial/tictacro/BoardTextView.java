package co.touchlab.robotiumtutorial.tictacro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import co.touchlab.robotiumtutorial.R;

/**
 * Created with IntelliJ IDEA.
 * User: matthewdavis
 * Date: 1/18/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
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
