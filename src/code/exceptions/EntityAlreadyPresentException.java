package code.exceptions;

public class EntityAlreadyPresentException extends IllegalArgumentException{
    
    String message;

    public EntityAlreadyPresentException(){}

    public EntityAlreadyPresentException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return "Entity already present";
    }    
    
}
