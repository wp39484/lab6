package com.example.lab6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import pl.antons.rsatest.R;

public class inTicTacToeBoard extends BaseAdapter {
    private Context context;
    private int player; //Current player (for move)
    private int[][] board = new int[3][3]; //game board

    //Constructor
    public inTicTacToeBoard(Context cont, String moves) {
        context = cont;

        //Filing empty board by moves history
        int mvs = 0;
        for (String move : moves.split("(?!^)")) {
            if (move != "") {
                try {
                    int parsedMove = Integer.parseInt(move) - 1;
                    int row = parsedMove / 3;
                    int col = parsedMove % 3;
                    this.move(col, row, mvs++ % 2);
                } catch (Exception e) {
                }
            }
        }
        //Setting Current Player
        player = mvs % 2;
    }

    //Method make move
    private boolean move(int col, int row, int player) {
        if (board[row][col] == 0) {
            board[row][col] = player + 1;
            return true;
        } else {
            return false;
        }
    }

    //Public method making move for playing user
    public inTicTacToeBoard add(int count) {
        int row = count / 3;
        int col = count % 3;
        //If change `player++%2` to `player` there is no switching between players
        if (this.move(col, row, player++ % 2))
            return this;
        return null;
        //return only when moves are correct
    }

    @Override //Must be in adapter
    public int getCount() {
        return 3 * 3;
    }

    @Override //Must be in adapter
    public Object getItem(int position) {
        return position + 1;
    }

    @Override //Must be in adapter
    public long getItemId(int position) {
        return position + 1;
    }

    //Method for generate view of singe element in greed
    @Override //Must be in adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create element - ImageView
        ImageView iv = new ImageView(context);
        //Calculate position of element into board array
        int col = position % 3;
        int row = position / 3;

        //Set appropriate image
        switch (board[row][col]) {
            case 0:
                iv.setImageResource(R.drawable.circle);
                break;
            case 1:
                iv.setImageResource(R.drawable.circle1);
                break;
            case 2:
                iv.setImageResource(R.drawable.player1);
                break;
            case 3:
                iv.setImageResource(R.drawable.player2);
                break;
        }

        //Seting size of image - 120x120 px
        iv.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
        return iv;
    }

    public int checkWin() {
        int check = 0;
        for (int i=0; i<=2; i++)
        {
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!=0)
            {
                return board[i][0];
            }
        }

        for(int i=0; i<=2; i++)
        {
            if (board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!=0)
            {
                return board[0][i];
            }
        }

        //Chceck rising horizontal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return 0;
    }

}
