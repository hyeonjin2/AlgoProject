package com.year2023.month08.day0823;

import java.util.Scanner;

// 애너그램 만들기
public class BaekJoon1919 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] a = sc.nextLine().toCharArray();
		char[] b = sc.nextLine().toCharArray();

		int[] countA = getAlphabetCount(a);
		int[] countB = getAlphabetCount(b);

		int cnt = 0;
		for (int i = 0; i < 26; i++) {
			cnt += Math.abs(countA[i] - countB[i]);
		}
		System.out.println(cnt);
	}

	public static int[] getAlphabetCount(char[] words) {
		int[] count = new int[26];
		for (char word : words) {
			count[word - 'a']++;
		}
		return count;
	}

}
