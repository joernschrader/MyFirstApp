package de.wvsg.myfirstapp;

import java.util.Random;

/**
 * Created by schrader on 19.07.14.
 */
public class TicTacToe {

    private final int EMPTY = 0;
    private final int CROSS = 1;
    private final int CIRCLE = 2;
    private int steps;
    private int[][] field = new int[3][3];
    private Random rnd;
    private int[] won = new int[3];
    private int lastComputerRow;
    private int lastComputerCol;


    /**
     * Konstruktor
     */
    public TicTacToe() {
        initializeGame();
    }


    /**
     * Gibt Anzahl der Schritte zurueck
     * @return Anzahl der Schritte
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Gib ID der letzten Reihe zurueck, die vom Computer befuellt wurde
     * @return ID der Reihe
     */
    public int getLastComputerRow() {
        return lastComputerRow;
    }

    /**
     * Gib ID der letzten Spalte zurueck, die vom Computer befuellt wurde
     * @return ID der Spalte
     */
    public int getLastComputerCol() {
        return lastComputerCol;
    }

    /**
     * Initialisiert ein neues TicTacToe, setzt alle Zellen auf LEER, setzt Zaehler zurueck
     */
    public void initializeGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                field[row][col] = EMPTY;
            }
        }
        steps = 0;
    }

    /**
     * Setzt Zelle auf angegeben Wert
     * @param row ID der Zeile
     * @param col ID der Spalte
     * @param value Wert
     */
    public void setCellValue(int row, int col, int value) {
        field[row][col] = value;
    }

    /**
     * Gibt Wert der Zelle zurueck
     * @param row ID der Zeile
     * @param col ID der Spalte
     * @return
     */
    public int getCellValue(int row, int col) {
        return field[row][col];
    }

    /**
     * Versucht, einen Wert in angebene Zelle zu schreiben,
     * erhoeht im Erfolgsfall den Zaehler
     * @param row ID der Zeile
     * @param col ID der Spalte
     * @param value Wert
     * @return FALSE, wenn Zelle nicht leer, TRUE sonst
     */
    public boolean trySetCellValue(int row, int col, int value) {
        if (steps < 9 && getCellValue(row, col) == EMPTY) {
            setCellValue(row, col, value);
            steps++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Simuliert einen Computerzug
     * @param value Wert, der als Computerzug eingetragen werden soll
     * @return FALSE, wenn Zelle nicht leer, TRUE sonst
     */
    public boolean tryComputerMove(int value) {
        if (steps < 9) {
            boolean done = false;
            int row, col;
            rnd = new Random(System.currentTimeMillis());
            while (!done) {
                row = rnd.nextInt(3);
                col = rnd.nextInt(3);
                if (trySetCellValue(row, col, value)) {
                    done = true;
                    lastComputerRow = row;
                    lastComputerCol = col;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ermittelt, ob es bereits einen Gewinner gibt.
     * @return ID des Gewinners
     */
    public int getWinner() {
        // Spalten pruefen
        for (int col = 0; col < 3; col++) {
            won[CIRCLE] = 0;
            won[CROSS] = 0;
            won[field[0][col]]++;
            won[field[1][col]]++;
            won[field[2][col]]++;
            if (won[CIRCLE] == 3) {
                return CIRCLE;
            } else if (won[CROSS] == 3) {
                return CROSS;
            }
        }
        // Zeilen pruefen
        for (int row = 0; row < 3; row++) {
            won[CIRCLE] = 0;
            won[CROSS] = 0;
            won[field[row][0]]++;
            won[field[row][1]]++;
            won[field[row][2]]++;
            if (won[CIRCLE] == 3) {
                return CIRCLE;
            } else if (won[CROSS] == 3) {
                return CROSS;
            }
        }
        // Diagonalen pruefen
        won[CIRCLE] = 0;
        won[CROSS] = 0;
        won[field[0][0]]++;
        won[field[1][1]]++;
        won[field[2][2]]++;
        if (won[CIRCLE] == 3) {
            return CIRCLE;
        } else if (won[CROSS] == 3) {
            return CROSS;
        }
        won[CIRCLE] = 0;
        won[CROSS] = 0;
        won[field[0][2]]++;
        won[field[1][1]]++;
        won[field[2][0]]++;
        if (won[CIRCLE] == 3) {
            return CIRCLE;
        } else if (won[CROSS] == 3) {
            return CROSS;
        }
        return EMPTY;
    }

}
