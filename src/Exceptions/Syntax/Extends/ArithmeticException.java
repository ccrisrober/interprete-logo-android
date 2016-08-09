package Exceptions.Syntax.Extends;

import Exceptions.Syntax.SyntaxException;

public class ArithmeticException extends SyntaxException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArithmeticException(String error) {
        super(error);
    }
    
}
