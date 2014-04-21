package passwordkata.domain;

public class EmptyPassword implements Password {

	@Override
	public String getText() {		
		return "";
	}

	@Override
	public boolean match(Password otherPassword) {		
		boolean theOtherIsAlsoEmpty = (otherPassword instanceof EmptyPassword);
		
		return theOtherIsAlsoEmpty;
	}

}
