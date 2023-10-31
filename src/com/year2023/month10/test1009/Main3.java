package com.year2023.month10.test1009;

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

	private static void perm(int cnt) {
		if (cnt == N) {
			boolean flag = true;
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (count > 1)
					flag = false;
				if (selected[i] == 0) {
					count++;
				} else {
					count = 0;
				}
			}
			if (flag && count < 2) {
				String result = "";
				for (int i = 0; i < N; i++) {
					result += selected[i];
				}
				System.out.println(result);
			}
			return;
		}

		for (int i = 1; i >= 0; i--) {
			selected[cnt] = i;
			perm(cnt + 1);
		}
	}

}
