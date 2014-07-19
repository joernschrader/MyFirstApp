package de.wvsg.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private final int EMPTY = 0;
    private final int CROSS = 1;
    private final int CIRCLE = 2;
    private ImageButton[][] btn = new ImageButton[3][3];
    private int[] won = new int[3];
    private String[] player = new String[3];
    private boolean gameFinished;
    private TicTacToe tictactoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Buttons
        btn[0][0] = (ImageButton) this.findViewById(R.id.ib00);
        btn[0][1] = (ImageButton) this.findViewById(R.id.ib01);
        btn[0][2] = (ImageButton) this.findViewById(R.id.ib02);
        btn[1][0] = (ImageButton) this.findViewById(R.id.ib10);
        btn[1][1] = (ImageButton) this.findViewById(R.id.ib11);
        btn[1][2] = (ImageButton) this.findViewById(R.id.ib12);
        btn[2][0] = (ImageButton) this.findViewById(R.id.ib20);
        btn[2][1] = (ImageButton) this.findViewById(R.id.ib21);
        btn[2][2] = (ImageButton) this.findViewById(R.id.ib22);
        player[1] = "Du";
        player[2] = "Computer";
        tictactoe = new TicTacToe();
        startNewGame();
    }

    private void startNewGame() {
        tictactoe.initializeGame();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                btn[row][col].setImageResource(android.R.color.transparent);
            }
        }
        ((Button) this.findViewById(R.id.btnNewGame)).setEnabled(false);
        gameFinished = false;
    }


    public void cellClicked(View view) {
        if (!gameFinished) {
            int row, col;
            ImageButton b = (ImageButton) view.findViewById(view.getId());
            switch (b.getId()) {
                case R.id.ib00:
                    row = 0;
                    col = 0;
                    break;
                case R.id.ib01:
                    row = 0;
                    col = 1;
                    break;
                case R.id.ib02:
                    row = 0;
                    col = 2;
                    break;
                case R.id.ib10:
                    row = 1;
                    col = 0;
                    break;
                case R.id.ib11:
                    row = 1;
                    col = 1;
                    break;
                case R.id.ib12:
                    row = 1;
                    col = 2;
                    break;
                case R.id.ib20:
                    row = 2;
                    col = 0;
                    break;
                case R.id.ib21:
                    row = 2;
                    col = 1;
                    break;
                case R.id.ib22:
                    row = 2;
                    col = 2;
                    break;
                default:
                    row = 0;
                    col = 0;
                    break;
            }
            if (tictactoe.trySetCellValue(row, col, CROSS)) {
                b.setImageResource(R.drawable.cross);
                if (checkWinner()) {
                    gameFinished = true;
                    return;
                }
                if (tictactoe.tryComputerMove(CIRCLE)) {
                    btn[tictactoe.getLastComputerRow()][tictactoe.getLastComputerCol()].setImageResource(R.drawable.circle);
                    if (checkWinner())
                        gameFinished = true;
                        return;
                }
            }
        }
        if (tictactoe.getSteps() == 9) {
            Toast.makeText(this.getBaseContext(), "Unentschieden", Toast.LENGTH_SHORT).show();
            ((Button) this.findViewById(R.id.btnNewGame)).setEnabled(true);
            gameFinished = true;
        }
    }

    private boolean checkWinner() {
        int winner = tictactoe.getWinner();
        if (winner != 0) {
            Toast.makeText(this.getBaseContext(), "Gewinner: " + player[winner], Toast.LENGTH_SHORT).show();
            ((Button) this.findViewById(R.id.btnNewGame)).setEnabled(true);
            return true;
        }
        return false;
    }


    public void btnNewGameClicked(View view) {
        startNewGame();
    }
}
