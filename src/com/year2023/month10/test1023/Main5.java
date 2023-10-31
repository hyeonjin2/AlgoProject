package com.year2023.month10.test1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < (N - i); j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] d = new int[N][N];
		d[0][0] = map[0][0];

		for (int j = 1; j < N; j++) {
			d[0][j] = d[0][j - 1] + map[0][j];
		}

		for (int i = 1; i < N; i++) {
			d[i][0] = d[i - 1][0] + map[i][0];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]) + map[i][j];
			}
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (max < d[i][j])
					max = d[i][j];
			}
		}
		System.out.println(max);
	}

}
