package com.day1217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 여행 가자
public class BaekJoon1976 {

	static int N, M;
	static String ans;
	static int[][] adjMatrix;
	static int[] trip;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjMatrix = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		trip = new int[M];
		for (int i = 0; i < M; i++) {
			trip[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		ans = "NO";
		goTrip();
		System.out.println(ans);
	}

	private static void goTrip() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(trip[0]);
		boolean[] visited = new boolean[N];
		visited[trip[0]] = true;
		int index = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (trip[index] == cur) {
				queue.clear();
				queue.offer(cur);
				visited = new boolean[N];
				visited[cur] = true;
				if (++index == M) {
					ans = "YES";
					return;
				}
			}
			for (int i = 0; i < N; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
	}

}
