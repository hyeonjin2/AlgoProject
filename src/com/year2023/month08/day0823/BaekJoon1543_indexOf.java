package com.year2023.month08.day0823;

import java.util.Scanner;

public class BaekJoon1543_indexOf {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String document = sc.nextLine();
		String word = sc.nextLine();

		int count = 0;
		int startIndex = 0;

		while (true) {
			int findIndex = document.indexOf(word, startIndex);

			if (findIndex < 0) {
				break;
			}
			count++;
			startIndex = findIndex + word.length();
		}

		System.out.println(count);
	}

}
