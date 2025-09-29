package com.lld.chess.app;

import com.lld.chess.game.ChessGame;

public class ChessApplication {
    public static void main(String[] args) {
        ChessGame game = new ChessGame();

        // Moves to reproduce a quick Fool's Mate (fastest checkmate in chess)
        // White moves (currentPlayer = WHITE)
        game.makeMove(6, 5, 5, 5);
        game.makeMove(1, 4, 3, 4);
        game.makeMove(6, 6, 4, 6);
        game.makeMove(0, 3, 4, 7);

        System.out.println("Game over? " + (game.isGameOver() ? "Yes" : "No"));
    }
}
