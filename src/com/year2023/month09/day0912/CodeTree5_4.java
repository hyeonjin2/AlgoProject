package com.year2023.month09.day0912;

import java.util.Scanner;

public class CodeTree5_4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		long[] d = new long[1001];
        d[0] = 1;
        d[1] = 2;

        for(int i = 2; i <= n; i++) {
            d[i] = (d[i - 1] * 2 + d[i - 2] * 3) % 1_000_000_007;
            for(int j = i - 3; j >= 0; j--)
                d[i] = (d[i] + d[j] * 2) % 1_000_000_007;
        }
		System.out.println(d[n]);
	}

}
