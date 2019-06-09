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
        context=cont;

        //Filing empty board by moves history
        int mvs = 0;
        for (String move:moves.split("(?!^)")) {
            if(move!=""){
                try{
                    this.move(Integer.parseInt(move), mvs++%2);
                }catch (Exception e) {
                }
            }
        }
        //Setting Current Player
        player = mvs%2;
    }

    //Method make move
    private boolean move(int col, int player)
    {
        return true;
    }

    //Public method making move for playing user
    public inTicTacToeBoard add(long col){
        //If change `player++%2` to `player` there is no switching between players
        if(this.move((int)col, player++%2))
            return this;
        return null;
        //return only when moves are correct
    }


    @Override //Must be in adapter
    public int getCount() {
        return 3*3;
    }
    @Override //Must be in adapter
    public Object getItem(int position) {
        return position%3;
    }
    @Override //Must be in adapter
    public long getItemId(int position) {
        return position%3;
    }

    //Method for generate view of singe element in greed
    @Override //Must be in adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create element - ImageView
        ImageView iv = new ImageView(context);
        //Calculate position of element into board array
        int col = position%3;
        int row = 1-position/3;

        //Set appropriate image
        switch (board[row][col]){
            case 0:
                iv.setImageResource(R.drawable.circle);
                break;
            case 1:
                iv.setImageResource(R.drawable.player1);
                break;
            case 2:
                iv.setImageResource(R.drawable.player2);
                break;
        }

        //Seting size of image - 120x120 px
        iv.setLayoutParams(new LinearLayout.LayoutParams(120,120));
        return iv;
    }

    //Method to check game state, if anybody wins, return which player win (or 0)
    public int checkWin(){
      return 1;
    }

}
