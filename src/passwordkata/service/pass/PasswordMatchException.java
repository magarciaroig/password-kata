package passwordkata.service.pass;

public class PasswordMatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PasswordMatchException(){
		this(null);
	}
	
	PasswordMatchException(String message){
		this(message, null);
		
	}
	PasswordMatchException(String message, Throwable cause){
		super(message, cause);
	}
}
