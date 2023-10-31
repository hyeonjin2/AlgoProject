package com.year2023.month10.day1016;

import java.util.Scanner;

// date to date
public class Codetree_sk2_1_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m1 = sc.nextInt();
		int d1 = sc.nextInt();
		int m2 = sc.nextInt();
		int d2 = sc.nextInt();

		int month = m1;
		int day = d1;

		int diffDays = 1;

		int[] numDay = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		while (true) {
			if (month == m2 && day == d2)
				break;
			diffDays++;
			day++;

			if (day > numDay[month]) {
				month++;
				day = 1;
			}
		}
		System.out.println(diffDays);
	}

}
