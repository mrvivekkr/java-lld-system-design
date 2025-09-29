package com.lld.chess.movestrategy;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class HorizontalVerticalMoveStrategy implements MoveStrategy {
    @Override
    public List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell) {
        List<Cell> moves = new ArrayList<>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        PieceColor color = currentCell.getCurrentPiece().getColor();

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];
            while (board.isValidPosition(r, c)) {
                Cell cell = board.getCell(r, c);
                if (cell.isEmpty()) {
                    moves.add(cell);
                } else {
                    if (cell.getCurrentPiece().getColor() != color) {
                        moves.add(cell); // enemy piece can be captured
                    }
                    break;
                }
                r += dir[0];
                c += dir[1];
            }
        }
        return moves;
    }
}
