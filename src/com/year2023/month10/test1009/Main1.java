package com.year2023.month10.test1009;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		if (a < b) {
			for (int i = a; i <= b; i++) {
				sb.append(i).append(" ");
			}
		} else {
			for (int i = b; i <= a; i++) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

}
