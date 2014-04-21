package passwordkata.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5SaltedPassword implements Password {

	private String text;
	private String salt;
	
	public Md5SaltedPassword(ClearPassword passwordData, String salt) throws NoSuchAlgorithmException{				
		
		this.text = md5(passwordData.getText(), salt);
		this.salt = salt;
	}
	
	public Md5SaltedPassword(String hashedText, String salt){
		this.text = hashedText;
		this.salt = salt;
	}
	
	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public boolean match(Password otherPassword) {		
		return this.text.equals(otherPassword.getText());
	}

	public String getSalt() {
		return salt;
	}	
	
	private String md5(String passwordText, String salt) throws NoSuchAlgorithmException {
        
        String md5 = null;
        String input = passwordText + salt;
                                             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        // Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        // Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);        
        
        return md5;
    }	

}
