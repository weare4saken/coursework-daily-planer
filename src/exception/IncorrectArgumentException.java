package exception;

import java.io.IOException;

public class IncorrectArgumentException extends IOException {

//    private final String argument;

    public IncorrectArgumentException(String message) { //, String argument) {
        super(message);
//        this.argument = argument;
    }

/*    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "IncorrectArgumentException{" +
                "argument='" + argument + '\'' +
                '}';
    }*/
}
