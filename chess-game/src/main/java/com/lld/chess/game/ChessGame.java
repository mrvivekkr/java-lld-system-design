package com.lld.chess.game;

import com.lld.chess.command.MoveCommand;
import com.lld.chess.command.StandardMoveCommand;
import com.lld.chess.enums.PieceColor;
import com.lld.chess.gamestate.GameStateEvaluator;
import com.lld.chess.model.*;

public class ChessGame {

    private ChessBoard board;
    private PieceColor currentPlayer;
    private GameStateEvaluator evaluator;
    private boolean gameOver = false;

    public ChessGame() {
        board = new ChessBoard();
        setupInitialBoard();
        currentPlayer = PieceColor.WHITE;  // White moves first
        evaluator = new GameStateEvaluator(board);
    }

    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!board.isValidPosition(fromRow, fromCol) || !board.isValidPosition(toRow, toCol)) {
            System.out.println("Invalid position(s).");
            return false;
        }

        Cell from = board.getCell(fromRow, fromCol);
        Cell to = board.getCell(toRow, toCol);
        Piece piece = from.getCurrentPiece();

        if (piece == null) {
            System.out.println("No piece at source.");
            return false;
        }

        if (piece.getColor() != currentPlayer) {
            System.out.println("It's not " + piece.getColor() + "'s turn.");
            return false;
        }

        MoveCommand command = new StandardMoveCommand(board, from, to);
        if (!command.validate()) {
            System.out.println("Invalid move.");
            return false;
        }

        command.execute();
        board.printBoard();
        switchTurn();

        // Now check if the player to move is in check or checkmate
        if (evaluator.isCheckmate(currentPlayer)) {
            System.out.println(currentPlayer + " is checkmated. Game over.");
            gameOver = true;
        } else if (evaluator.isInCheck(currentPlayer)) {
            System.out.println(currentPlayer + " is in check.");
        }

        return true;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoard getBoard() {
        return board;
    }

    private void setupInitialBoard() {
        // Place white pieces (row 7 and 6)
        board.getCell(7, 0).setCurrentPiece(new Rook(PieceColor.WHITE));
        board.getCell(7, 1).setCurrentPiece(new Knight(PieceColor.WHITE));
        board.getCell(7, 2).setCurrentPiece(new Bishop(PieceColor.WHITE));
        board.getCell(7, 3).setCurrentPiece(new Queen(PieceColor.WHITE));
        board.getCell(7, 4).setCurrentPiece(new King(PieceColor.WHITE));
        board.getCell(7, 5).setCurrentPiece(new Bishop(PieceColor.WHITE));
        board.getCell(7, 6).setCurrentPiece(new Knight(PieceColor.WHITE));
        board.getCell(7, 7).setCurrentPiece(new Rook(PieceColor.WHITE));

        for (int col = 0; col < 8; col++) {
            board.getCell(6, col).setCurrentPiece(new Pawn(PieceColor.WHITE));
        }

        // Place black pieces (row 0 and 1)
        board.getCell(0, 0).setCurrentPiece(new Rook(PieceColor.BLACK));
        board.getCell(0, 1).setCurrentPiece(new Knight(PieceColor.BLACK));
        board.getCell(0, 2).setCurrentPiece(new Bishop(PieceColor.BLACK));
        board.getCell(0, 3).setCurrentPiece(new Queen(PieceColor.BLACK));
        board.getCell(0, 4).setCurrentPiece(new King(PieceColor.BLACK));
        board.getCell(0, 5).setCurrentPiece(new Bishop(PieceColor.BLACK));
        board.getCell(0, 6).setCurrentPiece(new Knight(PieceColor.BLACK));
        board.getCell(0, 7).setCurrentPiece(new Rook(PieceColor.BLACK));

        for (int col = 0; col < 8; col++) {
            board.getCell(1, col).setCurrentPiece(new Pawn(PieceColor.BLACK));
        }

    }

    public boolean isGameOver() {
        return gameOver;
    }
}