# Chess Game Low-Level Design

## Overview

This project is a low-level design and implementation of the classic chess game in Java. It is designed as a modular, extensible system that simulates chess gameplay with core rules, legal move validation, turn management, board state visualization, and game state detection including check and checkmate conditions.

The implementation provides a strong foundation demonstrating key object-oriented design principles, use of design patterns, and separation of concerns suitable for interview preparation or foundational understanding of modeling complex game logic.

## Implementation Details

### Core Components

- **ChessBoard & Cell:** Represents the 8x8 board grid and individual cells holding pieces.
- **Piece Hierarchy:** Abstract `Piece` class with concrete subclasses (`King`, `Queen`, `Rook`, `Bishop`, `Knight`, `Pawn`) encapsulating piece-specific behavior.
- **MoveStrategy Pattern:** Each piece uses a `MoveStrategy` to calculate its legal moves (e.g., diagonal moves for bishop, L-shape for knight), applying the Strategy design pattern for flexible move calculations.
- **Command Pattern:** Move actions are encapsulated in `MoveCommand` implementations, enabling validation and execution with clear separation from game logic.
- **GameStateEvaluator:** Dedicated component for game state detection such as check and checkmate, allowing `ChessGame` to delegate state verifications.
- **ChessGame:** Orchestrates game flow, turn switching, move validation, execution, and game state notifications.
- **Board Printing:** The board can be printed after every move showing piece notations and empty squares for visualization in console.

### Design Patterns Used

- **Strategy Pattern:** For encapsulating different move calculation algorithms per piece.
- **Command Pattern:** For encapsulating and validating moves before execution.
- **Observer Pattern (Planned):** Although not currently implemented, the observer pattern is planned to decouple the core game logic from user interface or logging components. This pattern would enable observers to receive notifications on each move or game state change, such as printing the board or updating a UI.

## Features

- Legal move validation for all standard pieces.
- Turn-based play with enforcement of player color moves.
- Detection of check and checkmate conditions stopping the game.
- Console visualization of the chessboard with piece notation.
- Fool’s Mate example successfully implemented and tested.

## Next Enhancements

Several useful improvements can be made to further complete the chess game:

- **Special Moves Implementation:** Castling, en passant, and pawn promotion rules.
- **Observer Pattern Integration:** Observers that monitor game flow, including board-printing or UI components observing each move.
- **Move History and Undo/Redo:** Tracking moves, undoing actions, and replay functionality.

---

This project serves as a flexible, extensible framework well suited for iterative development and exploring complex system design in Java.

---

If you find any areas for improvement or have ideas for new features, please feel welcome to suggest improvements or raise change requests. Contributions, bug reports, and feature requests are highly appreciated!