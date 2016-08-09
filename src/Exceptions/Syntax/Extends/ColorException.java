package Exceptions.Syntax.Extends;

import Exceptions.Syntax.SyntaxException;

public class ColorException extends SyntaxException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1798957930878779088L;

	/**
	 * 
	 */
	public ColorException(String error) {
        super(error);
    }
    
}