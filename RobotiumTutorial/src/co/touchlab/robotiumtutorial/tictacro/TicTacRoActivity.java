package co.touchlab.robotiumtutorial.tictacro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import co.touchlab.robotiumtutorial.R;

/**
 * An activity that allows you to play Tic-Tac-Toe against Robotium!
 * Clicks are for user moves, Long Clicks are for Robot moves, no cheating!
 */
public class TicTacRoActivity extends Activity
{
    private TableLayout gameBoard;
    private TextView titleTextView;

    private boolean playerTurn = true;

    private final Character PLAYER_CHAR = 'X';
    private final Character ROBO_CHAR = 'O';

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(playerTurn && ((TextView)v).getText().length() < 1)
            {
                ((TextView)v).setText(PLAYER_CHAR.toString());
                titleTextView.setText(R.string.robot_turn);
                playerTurn = false;
                checkGameOver();
            }
        }
    };

    private View.OnLongClickListener longClickListener = new View.OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {
            if(!playerTurn && ((TextView)v).getText().length() < 1)
            {
                ((TextView)v).setText(ROBO_CHAR.toString());
                titleTextView.setText(R.string.player_turn);
                checkGameOver();
                return playerTurn = true;
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_ro);

        titleTextView = (TextView)findViewById(R.id.title_text_view);
        titleTextView.setText(R.string.player_turn);

        gameBoard = (TableLayout)findViewById(R.id.game_board);
        for(int i=0; i < gameBoard.getChildCount(); i++)
        {
            TableRow row = (TableRow)gameBoard.getChildAt(i);
            for(int j=0; j < row.getChildCount(); j++)
            {
                BoardTextView view = (BoardTextView)row.getChildAt(j);
                view.setOnClickListener(clickListener);
                view.setOnLongClickListener(longClickListener);
            }
        }
    }

    private boolean checkGameOver()
    {
        return false;
    }
}
