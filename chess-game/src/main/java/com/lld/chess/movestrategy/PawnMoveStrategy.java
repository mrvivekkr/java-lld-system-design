package com.lld.chess.movestrategy;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;
import com.lld.chess.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public List<Cell> calculatePossibleMoves(ChessBoard board, Cell currentCell) {
        List<Cell> moves = new ArrayList<>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        Piece pawn = currentCell.getCurrentPiece();  // or getCurrentPiece()
        PieceColor color = pawn.getColor();

        int direction = (color == PieceColor.WHITE) ? -1 : 1;

        // One step forward
        int forwardRow = row + direction;
        if (board.isValidPosition(forwardRow, col)) {
            Cell forwardCell = board.getCell(forwardRow, col);
            if (forwardCell.isEmpty()) {
                moves.add(forwardCell);

                // Two steps forward if pawn not moved
                if (!pawn.hasMoved()) {
                    int doubleStepRow = row + 2 * direction;
                    if (board.isValidPosition(doubleStepRow, col)) {
                        Cell doubleStepCell = board.getCell(doubleStepRow, col);
                        if (doubleStepCell.isEmpty()) {
                            moves.add(doubleStepCell);
                        }
                    }
                }
            }
        }

        // Capture diagonally left
        int diagLeftCol = col - 1;
        if (board.isValidPosition(forwardRow, diagLeftCol)) {
            Cell diagLeftCell = board.getCell(forwardRow, diagLeftCol);
            if (!diagLeftCell.isEmpty() && diagLeftCell.getCurrentPiece().getColor() != color) {
                moves.add(diagLeftCell);
            }
        }

        // Capture diagonally right
        int diagRightCol = col + 1;
        if (board.isValidPosition(forwardRow, diagRightCol)) {
            Cell diagRightCell = board.getCell(forwardRow, diagRightCol);
            if (!diagRightCell.isEmpty() && diagRightCell.getCurrentPiece().getColor() != color) {
                moves.add(diagRightCell);
            }
        }

        return moves;
    }
}
