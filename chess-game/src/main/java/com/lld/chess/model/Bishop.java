package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.DiagonalMoveStrategy;

public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(color, new DiagonalMoveStrategy());
    }

    @Override
    public char getNotation() {
        return 'B';
    }
}
