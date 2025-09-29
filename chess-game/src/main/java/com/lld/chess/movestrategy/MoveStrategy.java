package com.lld.chess.movestrategy;

import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.List;

public interface MoveStrategy {
    List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell);
}
