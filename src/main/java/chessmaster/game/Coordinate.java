package chessmaster.game;

import chessmaster.exceptions.ParseCoordinateException;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        if (isCoorOutofBoard(x, y)) {
            this.x = 0;
            this.y = 0;
            return;
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isCoorOutofBoard(int x, int y) {
        return (x < 0 || x >= ChessBoard.SIZE) || (y < 0 || y >= ChessBoard.SIZE);
    }

    public boolean isOffsetWithinBoard(int offsetX, int offsetY) {
        int newX = x + offsetX;
        int newY = y + offsetY;

        return (newX >= 0 && newX < ChessBoard.SIZE) && 
            (newY >= 0 && newY < ChessBoard.SIZE);
    }

    public Coordinate addOffsetToCoordinate(int offsetX, int offsetY) {
        int newX = x + offsetX;
        int newY = y + offsetY;

        if (isCoorOutofBoard(newX, newY)) {
            return new Coordinate(x, y);
        }

        return new Coordinate(newX, newY);
    }

    public static Coordinate parseAlgebraicCoor(String notation) throws ParseCoordinateException {
        notation = notation.toLowerCase();
        if (notation.length() != 2) {
            throw new ParseCoordinateException();
        }

        String BOARD_COLUMNS = "abcdefgh";
        String colString = Character.toString(notation.charAt(0));
        boolean isColValid = BOARD_COLUMNS.contains(colString);

        try {
            String rowString = String.valueOf(notation.charAt(1));
            int rowInt = Integer.parseInt(String.valueOf(rowString));

            if (rowInt < 1 || rowInt > ChessBoard.SIZE || !isColValid) {
                throw new ParseCoordinateException();
            }

            int x_index = (rowInt - 8) * -1;
            int y_index = BOARD_COLUMNS.indexOf(colString);

            return new Coordinate(x_index, y_index);

        } catch (NumberFormatException e) {
            throw new ParseCoordinateException();
        }
    }

}
