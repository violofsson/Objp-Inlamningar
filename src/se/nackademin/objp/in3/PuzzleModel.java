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

    List<Integer> boardState = new ArrayList<>();
    private List<Integer> solution;
    static final int EMPTYSPACE = 0;
    private final int COLUMNS;

    PuzzleModel(int rows, int cols) {
        COLUMNS = cols;
        for (int i = 1; i < rows * cols; i++) {
            boardState.add(i);
        }
        boardState.add(0);
        solution = new ArrayList<>(boardState);
    }

    boolean areAdjacent(int tileId1, int tileId2) {
        if (hasTile(tileId1) && hasTile(tileId2)) {
            int rowDiff = Math.abs(getRow(tileId1) - getRow(tileId2));
            int colDiff = Math.abs(getColumn(tileId1) - getColumn(tileId2));
            return (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 0);
        } else {
            throw new IllegalArgumentException();
        }
    }

    int getColumn(int tileId) {
        if (hasTile(tileId)) {
            return boardState.indexOf(tileId) % COLUMNS;
        } else {
            throw new IllegalArgumentException();
        }
    }

    int getRow(int tileId) {
        if (hasTile(tileId)) {
            return boardState.indexOf(tileId) / COLUMNS;
        } else {
            throw new IllegalArgumentException();
        }
    }

    boolean hasTile(int tileId) {
        return boardState.indexOf(tileId) != -1;
    }

    boolean isSolved() {
        return boardState.equals(solution);
    }

    void shuffle() {
        Collections.shuffle(boardState);
    }

    void swap(int tileId1, int tileId2) {
        if (hasTile(tileId1) && hasTile(tileId2)) {
            int index1 = boardState.indexOf(tileId1);
            int index2 = boardState.indexOf(tileId2);
            boardState.set(index1, tileId2);
            boardState.set(index2, tileId1);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
