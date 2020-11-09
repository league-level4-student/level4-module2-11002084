package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length() > s2.length()) {
			return s1;
		} else {
			return s2;
		}
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			return s.replace(' ', '_');
		} else {
			return s;
		}
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		
		String lastName1 = s1.substring(s1.indexOf(' ') + 1);
		String lastName2 = s2.substring(s2.indexOf(' ') + 1);
		String lastName3 = s3.substring(s3.indexOf(' ') + 1);
		
		if(lastName1.compareToIgnoreCase(lastName2) < 0 && lastName1.compareToIgnoreCase(lastName3) < 0){
			return s1;
		} else if(lastName2.compareToIgnoreCase(lastName1) < 0 && lastName2.compareToIgnoreCase(lastName3) < 0) {
			return s2;
		} else {
			return s3;
		}
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				sum += Character.getNumericValue(s.charAt(i));
			}
		}
		return sum;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int numAppearances = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == substring.charAt(0)){
				if(s.substring(i, i + substring.length()).equals(substring)) {
					numAppearances++;
				}
			}
		}
		return numAppearances;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		String[] words = s.split(" ");
		int numEnds = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].endsWith(substring)) {
				numEnds++;
			}
		}
		return numEnds;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		return s.lastIndexOf(substring) - s.indexOf(substring) - substring.length();
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String newString = "";
		for(int i = 0; i < s.length(); i++) {
			char character = Character.toLowerCase(s.charAt(i));
			if(character >= 97 && character <= 122) {
				newString += character;
			}
		}
		
		//0123
		//ABBA
		//ABA
		
		int firstHalfUpperBound;
		
		if(newString.length() % 2 == 0) {
			firstHalfUpperBound = newString.length()/2 - 1;
		} else {
			firstHalfUpperBound = newString.length()/2;
		}
		
		String firstHalf = newString.substring(0, firstHalfUpperBound + 1);
		String secondHalf = "";
		for(int i = newString.length() - 1; i >= newString.length()/2; i--) {
			secondHalf += newString.charAt(i);
		}
		
		return firstHalf.equals(secondHalf);
	}
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
