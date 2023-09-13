package com.year2023.month08.day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Codetree3_4 {

	static int N, M, K, max, safe, cnt;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		K = 1;
		
		for (int k = 1; k <= max; k++) {
			cnt = 0;
			int temp = safe;
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > k && !visited[i][j]) {
						dfs(i, j, k);
						cnt++;
					}
				}
			}
			safe = Math.max(safe, cnt);
			if (temp != safe) {
				K = k;
			}
		}

		System.out.println(K + " " + safe);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void dfs(int x, int y, int k) {
		visited[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] <= k)
				continue;

			dfs(nx, ny, k);
		}
	}

}
