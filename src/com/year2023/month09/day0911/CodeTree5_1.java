package com.year2023.month09.day0911;

import java.util.Scanner;

public class CodeTree5_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] d = new int[50];

		// n = 1, n = 2 �� �� �ʱ�ȭ
		d[1] = 1;
		d[2] = 1;

		for (int i = 3; i <= n; i++) {
			d[i] = d[i - 1] + d[i - 2];
		}
		System.out.println(d[n]);
	}

}
