package com.example.patentservice.utils;

public class PatentIdGenerator {

	public static String generateId(int number) {
		return String.format("P%04d", number + 1);
	}
}
