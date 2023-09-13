package com.year2023.month08.day0823;

import java.util.Scanner;

// 문서 검색
public class BaekJoon1543 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String document = sc.nextLine();
		String word = sc.nextLine();

		int count = 0;

		for (int i = 0; i < document.length(); i++) {
			boolean isSame = false;
			int length = 0;
			for (int j = 0; j < word.length(); j++) {
				if (i + j < document.length() && document.charAt(i + j) == word.charAt(j)) {
					length++;
					isSame = true;
				} else {
					length = 0;
					isSame = false;
				}
			}
			if (isSame && length == word.length()) {
				count++;
				i += word.length() - 1;
			}
		}
		System.out.println(count);
	}

}
