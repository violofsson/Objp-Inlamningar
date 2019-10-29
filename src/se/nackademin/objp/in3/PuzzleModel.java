package se.nackademin.objp.in3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleModel {
    // Obs att vi använder 0-index överallt utom på brickornas
    // "etiketter" - där motsvarar 0 det tomma utrymmet
    // I teorin kan vi ändra typ godtyckligt, så länge vi justerar
    // värdet på EMPTYSPACE och alltid har entydiga värden att jämföra
    // mellan spelplanen och lösningen

    protected List<Integer> boardState = new ArrayList<>();
    private List<Integer> solution;
    public static final int EMPTYSPACE = 0;
    private final int COLUMNS;

    PuzzleModel(int rows, int cols) {
        COLUMNS = cols;
        for (int i = 1; i < rows * cols; i++) {
            boardState.add(i);
        }
        boardState.add(0);
        solution = new ArrayList<>(boardState);
    }

    public boolean areAdjacent(int piece1, int piece2) {
        if (hasTile(piece1) && hasTile(piece2)) {
            int rowDiff = Math.abs(getRow(piece1) - getRow(piece2));
            int colDiff = Math.abs(getColumn(piece1) - getColumn(piece2));
            return (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 0);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getColumn(int piece) {
        if (hasTile(piece)) {
            return boardState.indexOf(piece) % COLUMNS;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getRow(int piece) {
        if (hasTile(piece)) {
            return boardState.indexOf(piece) / COLUMNS;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean hasTile(int tile) {
        return boardState.indexOf(tile) != -1;
    }

    public boolean isSolved() {
        return boardState.equals(solution);
    }

    public void shuffle() {
        Collections.shuffle(boardState);
    }

    void swap(int tile1, int tile2) {
        if (hasTile(tile1) && hasTile(tile2)) {
            int index1 = boardState.indexOf(tile1);
            int index2 = boardState.indexOf(tile2);
            boardState.set(index1, tile2);
            boardState.set(index2, tile1);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
