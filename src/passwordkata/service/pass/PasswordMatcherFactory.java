package passwordkata.service.pass;

import java.util.HashMap;

import passwordkata.domain.ClearPassword;
import passwordkata.domain.Md5SaltedPassword;
import passwordkata.domain.Password;

public class PasswordMatcherFactory {
		
	private static HashMap<Class<? extends Password>,PasswordMatcher> implementations;
	
	static {
		implementations = new HashMap<Class<? extends Password>,PasswordMatcher>();
		implementations.put(ClearPassword.class, new ClearPasswordMatcher());
		implementations.put(Md5SaltedPassword.class, new Md5SaltedPasswordMatcher());
	}
	
	public PasswordMatcher createPasswordMatcher(Password userPassword) throws PasswordMatchException {
		PasswordMatcher matcher = findPasswordMatcher(userPassword);
		
		boolean unknownMatcher = (matcher == null);
		if (unknownMatcher) throw new PasswordMatchException("Unable to find a mather to validate password of type " + userPassword.getClass().getName());
		
		return createMatcherFromAPreviousOne(matcher);	
	}
	
	private PasswordMatcher findPasswordMatcher(Password userPassword){
		PasswordMatcher matcher = null;
		
		Class<? extends Password> matcherKey = userPassword.getClass();
		if (implementations.containsKey(matcherKey)){
			matcher = implementations.get(matcherKey);
		}
		
		return matcher;
	}
	
	private PasswordMatcher createMatcherFromAPreviousOne(PasswordMatcher matcher) throws PasswordMatchException {
		PasswordMatcher newMatcher = null;
		
		try {
			newMatcher = matcher.clone();
		} catch (CloneNotSupportedException e) {
			throw new PasswordMatchException("Unexpected error cloning a new matcher of type " + matcher.getClass().getName() , e);
		}
		
		return newMatcher;
	}
}
