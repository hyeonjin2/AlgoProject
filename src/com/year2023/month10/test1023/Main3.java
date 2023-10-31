package com.year2023.month10.test1023;

import java.util.Scanner;

public class Main3 {

	static int N;
	static int[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		selected = new int[N];

		perm(0);
	}

	static void perm(int cnt) {
		if (cnt == N) {
			for (int i = 1; i < N; i++) {
				int diff = Math.abs(selected[i - 1] - selected[i]);
				if (diff != 1)
					return;
			}
			String answer = "";
			for (int i = 0; i < N; i++) {
				answer += selected[i];
			}
			System.out.println(answer);
			return;
		}
		for (int i = 1; i < 10; i++) {
			if (cnt > 0) {
				int diff = Math.abs(selected[cnt - 1] - i);
				if (diff != 1)
					continue;
			}
			selected[cnt] = i;
			perm(cnt + 1);
		}
	}

}
