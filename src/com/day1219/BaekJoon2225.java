package com.day1219;

import java.util.Arrays;
import java.util.Scanner;

// 합분해
public class BaekJoon2225 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		long[][] sum = new long[K + 1][N + 1];
		Arrays.fill(sum[0], 0);
		sum[0][0] = 1;
		for (int k = 1; k <= K; k++) {
			for (int i = 0; i <= N; i++) {
				for (int n = i; n <= N; n++) {
					sum[k][n] += sum[k - 1][n - i] % 1000000000;
				}
			}
		}
		System.out.println(sum[K][N] % 1000000000);
	}

}
