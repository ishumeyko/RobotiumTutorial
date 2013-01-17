package co.touchlab.robotiumtutorial;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Wrapper that does nothing but extend ImageView
 * Used for counting the number of instances of this particular class on the screen in DialogMenuWaitActivity's test
 */
public class WrappedImageView extends ImageView {
    public WrappedImageView(Context context) {
        super(context);
    }

    public WrappedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrappedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
