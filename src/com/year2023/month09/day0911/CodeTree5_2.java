package com.year2023.month09.day0911;

import java.util.Scanner;

public class CodeTree5_2 {

	public static void main(String[] args) {
		// 5 = 2 + 3 or 5 = 3 + 2
		// f(n) = f(n-2) + f(n-3)

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] d = new int[1001];

		d[1] = 0;
		d[2] = 1;
		d[3] = 1;

		for (int i = 4; i <= n; i++) {
			d[i] = d[i - 2] % 10_007 + d[i - 3] % 10_007;
		}

		System.out.println(d[n] % 10_007);
	}

}
