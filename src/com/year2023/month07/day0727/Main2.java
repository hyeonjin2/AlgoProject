package com.year2023.month07.day0727;

import java.util.Scanner;

public class Main2 {

	static int N, K;
	static int[] numbers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		numbers = new int[K];
		perm(0);

	}

	private static void perm(int cnt) {
		if (cnt == K) {
			for (int i = 0; i < K; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = N; i >= 1; i--) {
			numbers[cnt] = i;
			perm(cnt + 1);
		}
	}

}
