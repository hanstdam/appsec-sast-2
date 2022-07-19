package com.sonatype.infosec.owasp.a02.utilities;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordGenerator {
	
	private Stream<Character> getRandomSpecialChars(int count) {
		// CWE-331: Insufficient Entropy
	    Random random = new Random();
	    IntStream specialChars = random.ints(count, 33, 45);
	    return specialChars.mapToObj(data -> (char) data);
	}
	
	private Stream<Character> getRandomNumbers(int count) {
		// CWE-331: Insufficient Entropy
	    Random random = new Random();
	    IntStream numbers = random.ints(count, 48, 57);
	    return numbers.mapToObj(data -> (char) data);
	}
	
	private Stream<Character> getRandomUppercaseAlphabets(int count) {
		// CWE-331: Insufficient Entropy
	    Random random = new Random();
	    IntStream alphabets = random.ints(count, 65, 90);
	    return alphabets.mapToObj(data -> (char) data);
	}
	
	private Stream<Character> getRandomLowercaseAlphabets(int count) {
		// CWE-331: Insufficient Entropy
	    Random random = new Random();
	    IntStream alphabets = random.ints(count, 97, 122);
	    return alphabets.mapToObj(data -> (char) data);
	}
	
	public String get() {
		Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2), 
			Stream.concat(getRandomSpecialChars(2), 
			Stream.concat(getRandomUppercaseAlphabets(2), getRandomLowercaseAlphabets(4))));
		List<Character> charList = pwdStream.collect(Collectors.toList());
		Collections.shuffle(charList);
		
		String password = charList.stream()
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			.toString();
		return password;
	}
}
