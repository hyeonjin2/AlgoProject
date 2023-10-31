package com.year2023.month10.test1016;

import java.util.Scanner;

public class Main3 {

	static int N;
	static int[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		selected = new int[N];
		perm(0, 0, 0);
	}

	private static void perm(int cnt, int num1, int num2) {
		if (cnt == N) {
			if (num1 < 3 || num2 < 3)
				return;
			String result = "";
			for (int i = 0; i < N; i++) {
				result += selected[i];
			}
			System.out.println(result);
			return;
		}

		for (int i = 1; i <= 3; i++) {
			selected[cnt] = i;
			if (i == 1) {
				perm(cnt + 1, num1 + 1, num2);
			} else if (i == 2) {
				perm(cnt + 1, num1, num2 + 1);
			} else {
				perm(cnt + 1, num1, num2);
			}
		}

	}

}
