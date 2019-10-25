package se.nackademin.objp.in3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlideModel {
    // Obs att vi använder 0-index överallt utom på brickornas
    // "etiketter" - där motsvarar 0 det tomma utrymmet
    // I teorin kan vi ändra typ godtyckligt, så länge vi fortfarande
    // har entydiga värden att jämföra mellan spelbrädet och lösningen

    // TODO Dubbelkolla namnkonventioner
    // TODO Överväg för- och nackdelar med att skriva egna exceptions
    protected List<Integer> boardState = new ArrayList<>();
    private List<Integer> solution;
    private int rows;
    private int cols;

    SlideModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        for (int i = 1; i < rows * cols; i++) {
            boardState.add(i);
        }
        boardState.add(0);
        solution = new ArrayList<>(boardState);
        Collections.shuffle(boardState);
    }

    public int getColumn(int piece) {
        if (isValidPiece(piece)) {
            return boardState.indexOf(piece) % cols;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getRow(int piece) {
        if (isValidPiece(piece)) {
            return boardState.indexOf(piece) / cols;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isAdjacent(int piece1, int piece2) {
        if (isValidPiece(piece1) && isValidPiece(piece2)) {
            int rowDiff = Math.abs(getRow(piece1) - getRow(piece2));
            int colDiff = Math.abs(getColumn(piece1) - getColumn(piece2));
            return (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 0);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isSolved() {
        return boardState.equals(solution);
    }

    public boolean isValidPiece(int piece) {
        return boardState.indexOf(piece) != -1;
    }

    void swap(int piece1, int piece2) {
        if (isValidPiece(piece1) && isValidPiece(piece2)) {
            int index1 = boardState.indexOf(piece1);
            int index2 = boardState.indexOf(piece2);
            boardState.set(index1, piece2);
            boardState.set(index2, piece1);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
