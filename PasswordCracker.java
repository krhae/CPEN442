import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCracker {
	
	private static final boolean VERBOSE = false;
	
	public static String digitCracker(String sha1Hash, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException, InterruptedException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		String matchMe = "0000";
		String newHex = "";
		byte[] bytes;
		
		if(VERBOSE) {
			System.out.println(matchMe);
		}
		
		// Iterate through
		while(Integer.parseInt(matchMe) <= 9999) {
			
			bytes = (salt + matchMe).getBytes("UTF-8");
			md.update(bytes);
			bytes = md.digest();

			newHex = bytesToHexString(bytes);

			if(VERBOSE) {
			    System.out.println(newHex);				
			}
			
			if(newHex.equals(sha1Hash)) {
				System.out.println("Match Found!: " + matchMe);
				return matchMe;
			}
			
			matchMe = String.format("%04d", Integer.parseInt(matchMe) + 1);
			if(VERBOSE) {
				System.out.println("New matchMe: " + matchMe);				
			}
		}
		
		System.out.println("No match found.");
		return "";
	}
	
	
	public static String bytesToHexString(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    
	    for (byte b : bytes) {
	        sb.append(String.format("%02X", b));
	    }
	    
	    return sb.toString();
	}
	
	
	public static void main(String [] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InterruptedException {		
		digitCracker("DB96642EAA247C89574A86DF7BD1EABAC0D3C61F", "rM"); // Match Found: 5772
	}
	
}
