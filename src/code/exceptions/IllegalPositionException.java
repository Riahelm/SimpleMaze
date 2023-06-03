package code.exceptions;

public class IllegalPositionException extends IllegalArgumentException{

    public IllegalPositionException(){}

    public IllegalPositionException(String message){
        super(message);
    }

    public String toString(){
        return "Cannot access that position";
    }
}
