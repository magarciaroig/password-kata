package passwordkata.service;

import passwordkata.domain.ClearPassword;
import passwordkata.domain.Password;
import passwordkata.domain.User;
import passwordkata.service.pass.PasswordMatchException;
import passwordkata.service.pass.PasswordMatcher;
import passwordkata.service.pass.PasswordMatcherFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserAuthenticator  {
	
	PasswordMatcherFactory passwordMatcherFactory;
	
	public UserAuthenticator (PasswordMatcherFactory passwordMatcherFactory){
		this.passwordMatcherFactory = passwordMatcherFactory;
	}
		
	public boolean authenticate(User user, ClearPassword passwordToCompare) throws UserAuthenticationException{
		
		boolean thePasswordIsValid = false;
		Password userPassword = user.getPassword();		
		
		try {			
			PasswordMatcher passwordMatcher = this.passwordMatcherFactory.createPasswordMatcher(userPassword);
			
			thePasswordIsValid = passwordMatcher.match(userPassword, passwordToCompare);
			
		} catch (PasswordMatchException e) {
			
			throw new UserAuthenticationException("Error validating user password", e);
		}						
		
		return thePasswordIsValid;
	}
	
	public boolean authenticate(String cookieContent){
		//TODO Implement
		throw new NotImplementedException();
	}
	
}
