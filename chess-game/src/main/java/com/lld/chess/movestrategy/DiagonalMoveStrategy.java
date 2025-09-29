package com.lld.chess.movestrategy;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMoveStrategy implements MoveStrategy {

    @Override
    public List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell) {
        List<Cell> moves = new ArrayList<>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        PieceColor color = currentCell.getCurrentPiece().getColor();

        int[] directions = {-1, 1};

        // Explore four diagonal directions: (−1,−1), (−1,1), (1,−1), (1,1)
        for (int dr : directions) {
            for (int dc : directions) {
                int r = row + dr;
                int c = col + dc;
                while (board.isValidPosition(r, c)) {
                    Cell cell = board.getCell(r, c);
                    if (cell.isEmpty()) {
                        moves.add(cell);
                    } else {
                        if (cell.getCurrentPiece().getColor() != color) {
                            moves.add(cell); // can capture enemy piece
                        }
                        break;
                    }
                    r += dr;
                    c += dc;
                }
            }
        }

        return moves;
    }
}
