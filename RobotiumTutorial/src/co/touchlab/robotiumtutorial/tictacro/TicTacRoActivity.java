package co.touchlab.robotiumtutorial.tictacro;

import android.app.Activity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import co.touchlab.robotiumtutorial.R;

/**
 * Created with IntelliJ IDEA.
 * User: matthewdavis
 * Date: 1/17/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacRoActivity extends Activity
{
    private GridView gameBoard;
    private final int BOARDSIZE = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_ro);

        gameBoard = (GridView)findViewById(R.id.game_board);
        gameBoard.setAdapter(new BoardAdapter());
    }

    private class BoardAdapter extends BaseAdapter
    {
        private Boolean[] boardState = { null, true, false,
                                         false, true, true,
                                         false, false, true };

        @Override
        public int getCount()
        {
            return BOARDSIZE;
        }

        @Override
        public Object getItem(int i)
        {
            return stateToChar(boardState[i]);
        }

        private Character stateToChar(Boolean state)
        {
            if(state == null)
                return ' ';
            else if(state)
                return 'X';
            else
                return 'O';
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup)
        {
            if(convertView == null)
            {
                convertView = new TextView(TicTacRoActivity.this);
                convertView.setLayoutParams(new GridView.LayoutParams((int)(viewGroup.getWidth() * .33), (int)(viewGroup.getHeight() * .33)));
                ((TextView) convertView).setGravity(Gravity.CENTER);
                ((TextView) convertView).setTextSize(28);
                convertView.setPadding(8, 8, 8, 8);
            }

            ((TextView)convertView).setText(getItem(position).toString());
            return convertView;
        }
    }
}
