package com.year2023.month08.day0823;

import java.util.Scanner;

// 단어 공부
public class BaekJoon1157 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String a = sc.next().toUpperCase();
		int[] count = new int[26];

		for (int i = 0; i < a.length(); i++) {
			count[a.charAt(i) - 'A']++;
		}
		int max = 0;
		char alphabet = '?';
		for (int i = 0; i < 26; i++) {
			if (max < count[i]) {
				max = count[i];
				alphabet = (char) (i + 'A');
			} else if (max == count[i]) {
				alphabet = '?';
			}

		}
		System.out.println(alphabet);
	}

}
