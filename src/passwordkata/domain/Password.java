package passwordkata.domain;

public interface Password {
	
	public String getText();
	
	public boolean match(Password otherPassword);
}
