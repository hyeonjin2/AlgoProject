package com.year2023.month07.codebattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test1 {

	static int INF = 5_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] map = new int[N + 1][N + 1];

			for (int i = 0; i <= N; i++) {
				Arrays.fill(map[i], INF);
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				map[x][y] = c;
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (map[j][k] > map[j][i] + map[i][k])
							map[j][k] = map[j][i] + map[i][k];
					}
				}
			}

			int min = INF;

			for (int i = 1; i <= N; i++) {
				min = Math.min(min, map[i][i]);
			}
			if (min == INF) {
				min = -1;
			}
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

}
