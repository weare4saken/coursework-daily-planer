package exception;

public class IncorrectArgumentException extends Exception {

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
