# ChessInJava

A simple 2D Chess game implemented in Java using Swing. Focused on learning OOP, data structures, algorithms, and core Java features (enums, collections).

## Features
- Swing-based 2D chessboard UI
- All standard chess pieces and legal moves
- Turn-based play (White vs Black)
- Basic move validation (castling and promotion planned)
- Optional undo/redo

<!-- sym:## Project Structure -->
<a id="sym-project-structure"></a>
## Project Structure

- `chess.core` → Core chess logic (Board, Pieces, Moves)  
- `chess.gui` → Swing GUI (main window, board rendering)  
- `chess.util` → Utility classes and helpers  
- `assets/` → Piece image assets (optional)

Project layout:
```
ChessInJava/
│
├─ src/
│   ├─ chess/
│   │   ├─ core/        # Core chess logic classes
│   │   │   └─ ChessBoard.java
│   │   ├─ gui/         # Swing GUI classes
│   │   │   ├─ ChessGUI.java
│   │   │   └─ BoardPanel.java
│   │   └─ util/        # Helpers
│   │       └─ GameUtils.java
│
├─ assets/              # Piece images (optional)
├─ .gitignore
├─ README.md
└─ build/               # Eclipse build output (ignored)
```

## Learning Goals
- Practice Java OOP: inheritance, polymorphism, encapsulation  
- Implement data structures: arrays, lists, stacks  
- Algorithms: legal move generation, check/checkmate detection  
- Learn enums, collections, and event handling in Java

## Setup
1. Clone the repo
2. Open in Eclipse as a Java project
3. Run `chess.gui.ChessGUI` to start the game

(Anchor for direct linking on GitHub: #sym-project-structure)