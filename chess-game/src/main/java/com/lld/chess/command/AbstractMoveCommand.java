package com.lld.chess.command;

import com.lld.chess.model.Cell;
import com.lld.chess.model.ChessBoard;
import com.lld.chess.model.Piece;

public abstract class AbstractMoveCommand implements MoveCommand {
    protected ChessBoard board;
    protected Cell from;
    protected Cell to;
    protected Piece piece;

    public AbstractMoveCommand(ChessBoard board, Cell from, Cell to) {
        this.board = board;
        this.from = from;
        this.to = to;
        this.piece = from.getCurrentPiece();
    }
}
