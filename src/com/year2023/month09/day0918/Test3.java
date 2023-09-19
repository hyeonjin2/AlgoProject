package com.year2023.month09.day0918;

import java.util.Scanner;

public class Test3 {

	static int N;
	static int[] numbers = { 1, 2, 3 };
	static int[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		selected = new int[N];
		perm(0);
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			int num1 = 0, num2 = 0, num3 = 0;
			String number = "";
			for (int i = 0; i < N; i++) {
				if (selected[i] == 1) {
					num1++;
				} else if (selected[i] == 2) {
					num2++;
				} else {
					num3++;
				}
				number += selected[i];
			}
			if (num1 <= num2 && num2 <= num3) {
				System.out.println(number);
			}
			return;
		}
		for (int i = 0; i < 3; i++) {
			selected[cnt] = numbers[i];
			perm(cnt + 1);
		}
	}

}
