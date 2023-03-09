package code.exceptions;

public class IllegalPositionException extends IllegalArgumentException{
    
    String message;

    public IllegalPositionException(){};

    public IllegalPositionException(String message){
        super(message);
        this.message = message;
    };

    public String getMessage(){
        return message;
    }

    public String toString(){
        return "Cannot access that position";
    }
}
