package chess.core;

public class ModelTest {

    public static void main(String[] args) {

        ChessBoard board = new ChessBoard();

        // Example: testing the white knight at b1 (7,1)
        Piece knight = board.getPiece(new Position(7, 1));
        System.out.println("Knight at b1 legal moves:");

        knight.getLegalMoves(board).forEach(System.out::println);

        // Example: test rook movement after clearing a pawn
        board.setPiece(null, new Position(6, 0));
        Piece rook = board.getPiece(new Position(7, 0));

        System.out.println("\nRook at a1 after clearing pawn:");
        rook.getLegalMoves(board).forEach(System.out::println);
    }
}