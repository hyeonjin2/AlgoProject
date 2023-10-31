package com.year2023.month10.test1016;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		int sum = 0;
		for (int i = a; i <= b; i++) {
			if (i % c == 1)
				sum += i;
		}
		System.out.println(sum);
	}

}
