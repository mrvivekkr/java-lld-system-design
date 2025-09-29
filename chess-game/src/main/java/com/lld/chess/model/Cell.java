package com.lld.chess.model;

public class Cell {
    private final int row;
    private final int col;
    private Piece currentPiece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public boolean isEmpty() {
        return this.getCurrentPiece() == null;
    }
}
