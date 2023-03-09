package code.exceptions;

public class AbsentEntityException extends IllegalArgumentException {

    String message;

    public AbsentEntityException() {
    }

    ;

    public AbsentEntityException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return "No entity available";
    }


}
