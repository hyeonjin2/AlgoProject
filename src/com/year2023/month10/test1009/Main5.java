package com.year2023.month10.test1009;

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
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int d[][] = new int[N][N];
		d[N - 1][0] = map[N - 1][0];
		// j = N-1일 때 위로만 이동 가능
		d[N - 1][N - 1] = map[N - 1][N - 1];
		for (int i = N - 1; i >= 1; i--) {
			d[i][N - 1] += d[i + 1][N - 1];
		}
		

	}

	static int[] dx = { 0, -1 };
	static int[] dy = { 1, 0 };

}
