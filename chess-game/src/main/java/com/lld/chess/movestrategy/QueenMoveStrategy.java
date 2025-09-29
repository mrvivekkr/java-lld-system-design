package com.lld.chess.movestrategy;

import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class QueenMoveStrategy implements MoveStrategy {
    private final DiagonalMoveStrategy diagonal = new DiagonalMoveStrategy();
    private final HorizontalVerticalMoveStrategy horizontalVertical = new HorizontalVerticalMoveStrategy();

    @Override
    public List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell) {
        List<Cell> moves = new ArrayList<>(diagonal.calculatePossibleMoves(board, currentCell));
        moves.addAll(horizontalVertical.calculatePossibleMoves(board, currentCell));
        return moves;
    }
}
