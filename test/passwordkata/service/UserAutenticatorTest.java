package passwordkata.service;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import passwordkata.domain.ClearPassword;
import passwordkata.domain.Md5SaltedPassword;
import passwordkata.domain.User;
import passwordkata.service.pass.PasswordMatcherFactory;

public class UserAutenticatorTest {

	@Test
	public void testAuthenticateUserWithClearPassword() throws UserAuthenticationException {
		
		PasswordMatcherFactory matcherFactory = new PasswordMatcherFactory();
		UserAuthenticator authenticator = new UserAuthenticator(matcherFactory);
		
		String aRawTextPassword = "notmatters";
		ClearPassword anExpectedPassword = new ClearPassword(aRawTextPassword); 
		
		User aUser = new User("meganito", anExpectedPassword);				
		
		boolean expectedPasswordMatch = true;
		boolean obtainedPasswordMatch = authenticator.authenticate(aUser, anExpectedPassword);
		
		assertEquals("Unexpected failed auth with valid clear password", expectedPasswordMatch, obtainedPasswordMatch);
		
		ClearPassword anUnexpectedPassword = new ClearPassword("invalidpassword");
		expectedPasswordMatch = false;
		obtainedPasswordMatch = authenticator.authenticate(aUser, anUnexpectedPassword);
		
		assertEquals("Unexpected working auth with wrong clear password", expectedPasswordMatch, obtainedPasswordMatch);		
	}
	
	@Test
	public void testAuthenticateUserWithMd5SaltedPassword() throws UserAuthenticationException, NoSuchAlgorithmException {
		
		PasswordMatcherFactory matcherFactory = new PasswordMatcherFactory();
		UserAuthenticator authenticator = new UserAuthenticator(matcherFactory);
		
		String aRawTextPassword = "notmatters";
		ClearPassword anExpectedClearPassword = new ClearPassword(aRawTextPassword); 
		Md5SaltedPassword aMd5ExpectedPassword = new Md5SaltedPassword(anExpectedClearPassword, "aSaltKey");
		
		User aUser = new User("meganito", aMd5ExpectedPassword);				
		
		boolean expectedPasswordMatch = true;
		boolean obtainedPasswordMatch = authenticator.authenticate(aUser, anExpectedClearPassword);
		
		assertEquals("Unexpected failed auth with valid md5 password", expectedPasswordMatch, obtainedPasswordMatch);
		
		ClearPassword anUnexpectedClearPassword = new ClearPassword("invalidpassword");
		expectedPasswordMatch = false;
		obtainedPasswordMatch = authenticator.authenticate(aUser, anUnexpectedClearPassword);
		
		assertEquals("Unexpected working auth with wrong clear password", expectedPasswordMatch, obtainedPasswordMatch);	
		
	}

}
