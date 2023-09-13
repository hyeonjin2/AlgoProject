package com.year2023.month08.day0823;

import java.util.Scanner;

public class BaekJoon2744 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.nextLine().toCharArray();

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 'a') {
				arr[i] += 32;
			} else {
				arr[i] -= 32;
			}
		}
		System.out.println(arr);
	}

}
