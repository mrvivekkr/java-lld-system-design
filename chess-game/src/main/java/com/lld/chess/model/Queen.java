package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.QueenMoveStrategy;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(color, new QueenMoveStrategy());
    }

    @Override
    public char getNotation() {
        return 'Q';
    }
}
