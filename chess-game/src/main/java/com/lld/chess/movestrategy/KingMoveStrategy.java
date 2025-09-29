package com.lld.chess.movestrategy;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class KingMoveStrategy implements MoveStrategy {
    @Override
    public List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell) {
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };

        List<Cell> moves = new ArrayList<>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        PieceColor color = currentCell.getCurrentPiece().getColor();

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];
            if (board.isValidPosition(r, c)) {
                Cell cell = board.getCell(r, c);
                if (cell.isEmpty() || cell.getCurrentPiece().getColor() != color) {
                    moves.add(cell);
                }
            }
        }
        return moves;
    }
}
