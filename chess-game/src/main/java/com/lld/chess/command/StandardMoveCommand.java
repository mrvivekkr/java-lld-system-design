package com.lld.chess.command;

import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;

import java.util.List;

public class StandardMoveCommand extends AbstractMoveCommand {
    public StandardMoveCommand(ChessBoard board, Cell from, Cell to) {
        super(board, from, to);
    }

    @Override
    public boolean validate() {
        if (piece == null || piece == to.getCurrentPiece())
            return false;
        List<Cell> possibleMoves = piece.getPossibleMoves(board, from);
        return possibleMoves.contains(to);
    }

    @Override
    public void execute() {
        if (!validate())
            throw new IllegalStateException("Invalid Move");
        if (to.getCurrentPiece() != null)
            to.getCurrentPiece().setCaptured(true);
        to.setCurrentPiece(piece);
        from.setCurrentPiece(null);
        piece.setMoved(true);
    }
}
