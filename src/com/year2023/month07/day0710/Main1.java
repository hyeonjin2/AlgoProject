package com.year2023.month07.day0710;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {
	static int N;
	static int[] number;
	static Stack<String> stack;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		number = new int[N];
		stack = new Stack<>();
		perm(0);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			boolean flag = true;
			for (int i = 1; i < N; i++) {
				if (number[i - 1] == 0 && number[i] == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				String temp = "";
				for (int i = 0; i < N; i++) {
					temp = temp +  number[i];
				}
				stack.push(temp);
			}
			return;
		}
		for (int i = 0; i <= 1; i++) {
			number[cnt] = i;
			perm(cnt + 1);
		}
	}

}
