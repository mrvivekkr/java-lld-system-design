package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.HorizontalVerticalMoveStrategy;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color, new HorizontalVerticalMoveStrategy());
    }

    @Override
    public char getNotation() {
        return 'R';
    }
}
