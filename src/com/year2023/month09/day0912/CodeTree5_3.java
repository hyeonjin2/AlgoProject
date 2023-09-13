package com.year2023.month09.day0912;

import java.util.Scanner;

public class CodeTree5_3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] d = new int[1001];
		d[1] = 1;
		d[2] = 2;

		for (int i = 3; i <= n; i++) {
			d[i] = (d[i - 1] + d[i - 2]) % 10_007;
		}
		System.out.println(d[n]);
	}

}
