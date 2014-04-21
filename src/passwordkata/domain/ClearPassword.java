package passwordkata.domain;

public class ClearPassword implements Password {
	
	private String text;
	
	public ClearPassword (String text){
		this.text = text;
	}

	@Override
	public String getText() {		
		return this.text;
	}

	@Override
	public boolean match(Password otherPassword) {		
		return this.text.equals(otherPassword.getText());
	}		
	
	public boolean isSecure(){
		final boolean amISecure = amIEnoughLonger() 
				&& haveIGotAnyDigit() 
				&& haveIGotAnyNonLetterAndNonDigit(); 
		
		return amISecure;
	}
	
	private boolean amIEnoughLonger()
	{
		final byte minimumLength = 8;
		
		boolean theLengthIsEnough = (this.text.length() >= minimumLength);
		
		return theLengthIsEnough; 
	}
	
	private boolean haveIGotAnyDigit()
	{
		return this.text.matches(".*\\d+.*");
	}
	
	private boolean haveIGotAnyNonLetterAndNonDigit()
	{
		return this.text.matches(".*[^0-9a-zA-Z]+.*");
	}	
}
