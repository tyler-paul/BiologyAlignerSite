package tylerpaul.bio.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class Hasher {
	public static String md5Hash(String plaintext) {
		String hash = null;
		try {
			byte[] bytesOfMessage = plaintext.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			hash = DatatypeConverter.printHexBinary(thedigest);
		}
		catch (Exception e) {
		}
		return hash;
	}
}
