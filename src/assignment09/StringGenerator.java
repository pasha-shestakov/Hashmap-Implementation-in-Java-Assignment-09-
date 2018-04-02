package assignment09;

import java.util.Random;

public class StringGenerator {
	private final static String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	private static Random rand = new Random();
	
	public static String generateString() {
		
		int stringLength = randomStringLength();
		
		StringBuilder strBuilder = new StringBuilder(stringLength);
		
		for (int index = 0; index < stringLength; index++) {
			strBuilder.append(STR.charAt(rand.nextInt(STR.length())));
		}
		
		return strBuilder.toString();
	}
	
	/**
	 * Generate random string length with mean of 7 and standard deviation of 2
	 * 
	 * @return
	 */
	private static int randomStringLength() {
		int length = (int) (rand.nextGaussian() * 2 + 7);
		if (length <= 0) {
			return 1;
		}
		else {
			return length;
		}
	}
}
