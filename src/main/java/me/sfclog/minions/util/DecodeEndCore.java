package me.sfclog.minions.util;
import java.util.Base64;

public class DecodeEndCore {
	public static String endcode(String s) {
		byte[] bytesEncoded = Base64.getEncoder().encode(s.getBytes());
		
		return new String(bytesEncoded);
	}
	public static String decode(String s) {
		byte[] valueDecoded = Base64.getDecoder().decode(s.getBytes());
		return new String(valueDecoded);
	}
}
