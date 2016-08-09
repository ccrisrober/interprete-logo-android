package Exceptions.Interpreter;

public class InterpreterException extends RuntimeException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterpreterException(String error) {
        super(error);
    }
    
}
