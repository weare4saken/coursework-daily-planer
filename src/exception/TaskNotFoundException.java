package exception;

import java.io.IOException;

public class TaskNotFoundException extends IOException {
    public TaskNotFoundException(String message) { super(message); }
}
