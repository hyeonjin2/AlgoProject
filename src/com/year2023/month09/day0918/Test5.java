package com.year2023.month09.day0918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test5 {

	static int N;
	static int[][] map, d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		d = new int[N][N];

		move(0, N - 1);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				[i-1][j] or [i][j+1]
				if (i - 1 > 0 && j + 1 < N) {
					d[i][j] = Math.max(map[i - 1][j], map[i][j + 1]) + map[i][j];
				} else if (i - 1 > 0) {
					d[i][j] = map[i - 1][j] + map[i][j];
				} else if (j + 1 < N) {
					d[i][j] = map[i][j + 1] + map[i][j];
				}
			}
		}
		System.out.println(Arrays.deepToString(d));
		System.out.println(d[N - 1][0]);
	}

	private static void move(int i, int j) {
//		[i-1][j], [i][j+1]
		if (i + 1 < N && j - 1 > 0) {
			
		}
	}

}
