package com.year2023.month08.day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Codetree3_1 {

	static int N, M, cnt;
	static int[][] adjMatrix;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjMatrix = new int[N + 1][N + 1];

		// ������� �����
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}

		visited = new boolean[N + 1];
		dfs(1);
		System.out.println(cnt);
	}

	private static void dfs(int start) {
		visited[start] = true;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adjMatrix[start][i] != 0) {
				dfs(i);
				cnt++;
			}
		}
	}

}
