package chessmaster.exceptions;

public class InvalidMoveException extends ChessMasterException {
    public InvalidMoveException() {
        super(ExceptionMessages.MESSAGE_INVALID_MOVE_EXCEPTION);
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}
