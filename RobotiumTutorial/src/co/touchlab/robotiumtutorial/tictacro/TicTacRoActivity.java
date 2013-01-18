package co.touchlab.robotiumtutorial.tictacro;

import android.app.Activity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MotionEvent;
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

        //Hack so that the Grid doesn't scroll
        //todo - GridView was probably a bad decision here, it isn't really working TableLayout refactor?
        gameBoard.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    return true;
                }
                return false;
            }

        });
    }

    private class BoardAdapter extends BaseAdapter
    {
        private Boolean[] boardState = new Boolean[BOARDSIZE];
        private boolean playerTurn = true;

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
                convertView.setLayoutParams(new GridView.LayoutParams((int)(viewGroup.getWidth() * .30), (int)(viewGroup.getHeight() * .30)));
                convertView.setBackgroundColor(R.color.dark_green);
                ((TextView) convertView).setGravity(Gravity.CENTER);
                ((TextView) convertView).setTextSize(28);
                //convertView.setPadding(8, 8, 8, 8);

                convertView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(playerTurn)
                        {
                            boardState[(Integer)v.getTag()] = true;
                            notifyDataSetChanged();
                            playerTurn = false;
                        }
                    }
                });
                convertView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        boardState[(Integer) v.getTag()] = false;
                        notifyDataSetChanged();
                        return playerTurn = true;
                    }
                });
            }

            convertView.setTag(position);
            ((TextView)convertView).setText(getItem(position).toString());
            return convertView;
        }
    }
}
