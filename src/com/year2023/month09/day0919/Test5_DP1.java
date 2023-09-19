package com.year2023.month09.day0919;

import java.util.Scanner;

public class Test5_DP1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// d[i]: 2*i 크기의 사각형을 채우는 방법의 수
		int[] d = new int[1001];

		// d[i] = d[i-1]+d[i-2]*2
		d[0] = 1;
		d[1] = 1;

		for (int i = 2; i <= N; i++) {
			d[i] = (d[i - 1] + d[i - 2] * 2) % 10_007;
		}
		System.out.println(d[N]);
	}

}
