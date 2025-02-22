package chessmaster.ui;

import java.io.PrintStream;
import java.util.Scanner;

import chessmaster.game.ChessBoard;
import chessmaster.game.ChessTile;
import chessmaster.game.Coordinate;

public final class TextUI {

    public static final String CHESS_BOARD_DIVIDER = "_".repeat(4 * ChessBoard.SIZE + 1);

    private static final String COLUMN_HEADER = "abcdefgh";

    /**
     * Format of a comment input line. Comment lines are silently consumed when
     * reading user input.
     */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream out = System.out;

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * 
     * @return command (full line) entered by the user
     */
    public static String getUserInput() {
        String fullInputLine = scanner.nextLine().trim();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = scanner.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or
     * is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private static boolean shouldIgnore(String rawInputLine) {
        boolean isCommentLine = rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
        return rawInputLine.trim().isEmpty() || isCommentLine;
    }

    public static void printPromotePrompt(Coordinate coord){
        String message = String.format(UiMessages.PROMPT_PROMOTE_MESSAGE, coord.toString());
        out.println(message);
    }

    public static void printPromoteInvalidMessage(){
        out.println(UiMessages.PROMPT_PROMOTE_INVALID_MESSAGE);
    }

    public static void printChessBoardDivider() {
        out.println(CHESS_BOARD_DIVIDER);
    }

    public static void printChessBoardHeader() {
        for (int i = 0; i < COLUMN_HEADER.length(); i++) {
            char col = COLUMN_HEADER.charAt(i);
            out.printf(" (%s)", col);
        }
        out.println("");
    }

    public static void printChessBoardRow(int rowNum, String chessBoardRow) {
        out.print(chessBoardRow);
        out.print(ChessTile.TILE_DIVIDER);
        out.print(String.format(" (%d)", rowNum));
        out.print(System.lineSeparator() + CHESS_BOARD_DIVIDER);
        out.println("");
    }

    public static void printErrorMessage(Exception e) {
        out.println(e.getMessage());
    }

}
