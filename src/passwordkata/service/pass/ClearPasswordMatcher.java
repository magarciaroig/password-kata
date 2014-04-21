package passwordkata.service.pass;

import passwordkata.domain.ClearPassword;
import passwordkata.domain.Password;

public class ClearPasswordMatcher implements PasswordMatcher {

	@Override
	public boolean match(Password source, ClearPassword toCompare)  {
		
		boolean unexpectedPasswordToCompareType = ! (source instanceof ClearPassword);
		if (unexpectedPasswordToCompareType ) return false;
		
		ClearPassword clearSourcePassword = (ClearPassword) source;
		
		return clearSourcePassword.match(toCompare);		
	}

	@Override
	public PasswordMatcher clone() throws CloneNotSupportedException {				
		
		return (PasswordMatcher) super.clone();						
	}		

}
