package com.lld.chess.movestrategy;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class LShapeMoveStrategy implements MoveStrategy {
    @Override
    public List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell) {
        int[][] moves = {
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
        };

        List<Cell> possibleMoves = new ArrayList<>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        PieceColor color = currentCell.getCurrentPiece().getColor();

        for (int[] move : moves) {
            int r = row + move[0];
            int c = col + move[1];
            if (board.isValidPosition(r, c)) {
                Cell cell = board.getCell(r, c);
                if (cell.isEmpty() || cell.getCurrentPiece().getColor() != color) {
                    possibleMoves.add(cell);
                }
            }
        }
        return possibleMoves;
    }
}
