package passwordkata.service.pass;

import passwordkata.domain.ClearPassword;
import passwordkata.domain.Password;

public interface PasswordMatcher extends Cloneable {

	public boolean match(Password source, ClearPassword toCompare) throws PasswordMatchException;
	
	public PasswordMatcher clone() throws CloneNotSupportedException;

}