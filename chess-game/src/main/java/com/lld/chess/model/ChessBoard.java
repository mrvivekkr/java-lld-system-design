package com.lld.chess.model;

public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private Cell[][] board;

    public ChessBoard() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = new Cell(row, col);
            }
        }
    }

    // Returns true if given row and column are inside the board boundaries
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    // Returns the Cell instance at the specified position
    public Cell getCell(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid board position");
        }
        return board[row][col];
    }

    public void printBoard() {
        System.out.println("==================");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("|");
            for (int col = 0; col < BOARD_SIZE; col++) {
                Cell cell = board[row][col];
                Piece piece = cell.getCurrentPiece();
                char notation = (piece == null) ? '.' : piece.getNotation();
                System.out.print(notation + " ");
            }
            System.out.println("|");
        }
        System.out.println("==================");
    }

}
