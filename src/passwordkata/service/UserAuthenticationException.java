package passwordkata.service;

public class UserAuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserAuthenticationException(){
		this(null);
	}
	
	UserAuthenticationException(String message){
		this(message, null);
		
	}
	UserAuthenticationException(String message, Throwable cause){
		super(message, cause);
	}
}
