package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.LShapeMoveStrategy;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color, new LShapeMoveStrategy());
    }

    @Override
    public char getNotation() {
        return 'k';
    }
}
