package com.lld.chess.model;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.movestrategy.PawnMoveStrategy;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(color, new PawnMoveStrategy());
    }

    @Override
    public char getNotation() {
        return 'P';
    }
}
