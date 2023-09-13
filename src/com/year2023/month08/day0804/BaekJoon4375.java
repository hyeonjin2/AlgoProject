package com.year2023.month08.day0804;

import java.util.Scanner;

// 1
public class BaekJoon4375 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();

			int target = 1;
			int digit = 1;
//			System.out.println(n);

			while (target % n != 0) {
				target = (target * 10 + 1) % n;
				digit++;
			}
			System.out.println(digit);
		}
	}

}
