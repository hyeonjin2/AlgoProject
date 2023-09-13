package com.year2023.month09.day0910;

import java.util.Scanner;

public class Main2 {

	static int N;
	static int[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		selected = new int[N];
		subset(0);
	}

	private static void subset(int index) {
		if (index == N) {
			for (int i = 1; i < N; i++) {
				if (selected[i - 1] == 0 && selected[i] == 0) {
					return;
				}
			}
			String output = "";
			for (int i = 0; i < N; i++) {
				output = output + selected[i];
			}
			System.out.println(output);
			return;
		}
		selected[index] = 0;
		subset(index + 1);
		selected[index] = 1;
		subset(index + 1);
	}

}
