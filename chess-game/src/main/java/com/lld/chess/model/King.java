package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.KingMoveStrategy;

public class King extends Piece{
    public King(PieceColor color) {
        super(color, new KingMoveStrategy());
    }

    @Override
    public char getNotation() {
        return 'K';
    }
}
