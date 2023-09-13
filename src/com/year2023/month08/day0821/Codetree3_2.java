package com.year2023.month08.day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Codetree3_2 {

	static int N, M, result;
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
			}
		}

		visited = new boolean[N][M];
		dfs(0, 0);

		System.out.println(result);
	}

	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	private static void dfs(int x, int y) {
		visited[x][y] = true;

		if (x == N - 1 && y == M - 1) {
			result = 1;
			return;
		}
		for (int d = 0; d < 2; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] == 0)
				continue;

			dfs(nx, ny);
		}
	}

}
