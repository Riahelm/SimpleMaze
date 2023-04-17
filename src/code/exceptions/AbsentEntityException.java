package code.exceptions;

import java.util.NoSuchElementException;

public class AbsentEntityException extends NoSuchElementException {

    String message;

    public AbsentEntityException(){}

    public String getMessage() {
        return message;
    }

    public String toString() {
        return "No entity available";
    }


}
