package com.year2023.month09.day0910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		int[] d = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			d[i] = map[1][i];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j - 1 < 0) {
					System.out.println("before : " + d[j]);
					d[j] += Math.max(map[i - 1][j], map[i - 1][j + 1]);
					System.out.println("after : " + map[i - 1][j] + " " + map[i - 1][j + 1] + " " + d[j]);
				} else if (j + 1 >= N) {
					d[j] += Math.max(map[i - 1][j - 1], map[i - 1][j]);
				} else {
					d[j] += Math.max(map[i - 1][j - 1], Math.max(map[i - 1][j], map[i - 1][j + 1]));
				}
				System.out.println(d[j]);
			}
			System.out.println(Arrays.toString(d));
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			result = Math.max(result, d[i]);
		}
		System.out.println(result);
	}

}
