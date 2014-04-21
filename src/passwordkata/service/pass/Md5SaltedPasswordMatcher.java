package passwordkata.service.pass;

import java.security.NoSuchAlgorithmException;

import passwordkata.domain.ClearPassword;
import passwordkata.domain.Md5SaltedPassword;
import passwordkata.domain.Password;

public class Md5SaltedPasswordMatcher implements PasswordMatcher {

	@Override
	public boolean match(Password source, ClearPassword toCompare) throws PasswordMatchException {
		
		boolean unexpectedPasswordToCompareType = ! (source instanceof Md5SaltedPassword);
		if (unexpectedPasswordToCompareType) return false;
		
		Md5SaltedPassword md5SourcePassword = (Md5SaltedPassword) source;
		
		try {
			
			Md5SaltedPassword md5ToComparePassword = new Md5SaltedPassword(toCompare, md5SourcePassword.getSalt());
			
			return md5SourcePassword.match(md5ToComparePassword);
			
		} catch (NoSuchAlgorithmException e) {
			throw new PasswordMatchException();
		}				
	}
	
	@Override
	public PasswordMatcher clone() throws CloneNotSupportedException {				
		
		return (PasswordMatcher) super.clone();						
	}

}
