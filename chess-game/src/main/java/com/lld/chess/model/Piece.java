package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.MoveStrategy;

import java.util.List;

public abstract class Piece {
    protected final PieceColor color;
    protected boolean hasMoved;
    protected boolean isCaptured;
    protected MoveStrategy moveStrategy;

    public Piece(PieceColor color, MoveStrategy moveStrategy){
        this.color = color;
        this.hasMoved = false;
        this.isCaptured = false;
        this.moveStrategy = moveStrategy;
    }

    public abstract char getNotation();

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(Boolean captured){
        this.isCaptured = captured;
    }

    public List<Cell> getPossibleMoves(ChessBoard board, Cell currentCell) {
        return moveStrategy.calculatePossibleMoves(board, currentCell);
    }
}
