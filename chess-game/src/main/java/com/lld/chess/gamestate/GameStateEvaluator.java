package com.lld.chess.gamestate;

import com.lld.chess.enums.PieceColor;
import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;
import com.lld.chess.model.King;
import com.lld.chess.model.Piece;

import java.util.List;

public class GameStateEvaluator {

    private final ChessBoard board;

    public GameStateEvaluator(ChessBoard board) {
        this.board = board;
    }

    // Check if the king of the specified color is currently in check
    public boolean isInCheck(PieceColor color) {
        Cell kingCell = findKingCell(color);
        if (kingCell == null) {
            throw new IllegalStateException("King not found on the board.");
        }
        return isCellUnderAttack(kingCell, color);
    }

    private Cell findKingCell(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = board.getCell(row, col);
                Piece occupant = cell.getCurrentPiece();
                if (occupant != null && occupant.getColor() == color && occupant instanceof King) {
                    return cell;
                }
            }
        }
        return null;
    }

    private boolean isCellUnderAttack(Cell cell, PieceColor defenderColor) {
        PieceColor attackerColor = (defenderColor == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell attackerCell = board.getCell(row, col);
                Piece attacker = attackerCell.getCurrentPiece();
                if (attacker != null && attacker.getColor() == attackerColor) {
                    List<Cell> attackerMoves = attacker.getPossibleMoves(board, attackerCell);
                    if (attackerMoves.contains(cell)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Check if the king of given color is checkmated (in check with no escape)
    public boolean isCheckmate(PieceColor color) {
        return isInCheck(color) && !hasAnyLegalMoves(color);
    }

    // Checks if player has any move that can get them out of check
    private boolean hasAnyLegalMoves(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = board.getCell(row, col);
                Piece piece = cell.getCurrentPiece();
                if (piece != null && piece.getColor() == color) {
                    List<Cell> moves = piece.getPossibleMoves(board, cell);
                    for (Cell move : moves) {
                        if (wouldMoveBeSafe(cell, move, color)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Simulate the move to check if the king is still safe
    private boolean wouldMoveBeSafe(Cell from, Cell to, PieceColor color) {
        Piece movingPiece = from.getCurrentPiece();
        Piece capturedPiece = to.getCurrentPiece();

        // Execute move
        to.setCurrentPiece(movingPiece);
        from.setCurrentPiece(null);

        boolean safe = !isInCheck(color);

        // Undo move
        from.setCurrentPiece(movingPiece);
        to.setCurrentPiece(capturedPiece);

        return safe;
    }


}
