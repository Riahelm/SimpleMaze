package code.exceptions;

public class AbsentEntityException extends IllegalArgumentException {

    String message;

    public AbsentEntityException(){}

    public String getMessage() {
        return message;
    }

    public String toString() {
        return "No entity available";
    }


}
