package com.year2023.month10.day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 영준이의 진짜 BFS
public class SWEA1855 {

	static int N;
	static List<List<Integer>> graph;
	static int[][] parents; // parents[i][j] : i번째 노드의 2^j번째 부모
	static int[] d; // d[i] : i번 노드의 tree 깊이 (depth)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			// 인접 리스트 초기화
			graph = new ArrayList<>();
			for (int i = 0; i < 100001; i++) {
				graph.add(new ArrayList<>());
			}

			// parents, d 배열 초기화
			parents = new int[100001][20];
			d = new int[100001];

			parents[1][0] = 0;
			d[1] = 0;

			N = Integer.parseInt(br.readLine());
			// 인접 리스트 채우기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 2; i <= N; i++) {
				int parent = Integer.parseInt(st.nextToken());
				graph.get(parent).add(i);
				parents[i][0] = parent;
				d[i] = d[parent] + 1;
			}
			// parents[i][j] : i번째 노드의 2^j번째 부모
			// p[i][5] = p[p[i][4]][4] : i의 32번째 부모는 i의 16번째 부모의 또 16번째 부모
			for (int j = 1; j < 20; j++) {
				for (int i = 1; i <= N; i++) {
					parents[i][j] = parents[parents[i][j - 1]][j - 1];
				}
			}
			long answer = bfs();
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static long bfs() {
		long result = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		queue.offer(1);
		visited[1] = true;

		int prev = 1;
		int cur = 0;
		while (!queue.isEmpty()) {
			int curNode = queue.poll();
			for (int i = 0; i < graph.get(curNode).size(); i++) {
				int child = graph.get(curNode).get(i);
				if (visited[child])
					continue;
				queue.offer(child);
				visited[child] = true;
				cur = child;
				result += LCA(prev, cur, 0);
				prev = cur;
			}
		}
		return result;
	}

	// 공통조상 찾아서 거리 계산하기
	private static long LCA(int x, int y, int cnt) {
		// x와 y의 깊이 고정하기 -> y가 더 깊을 때로 고정
		if (d[x] > d[y]) {
			swap(x, y);
		}
		// y의 부모가 x이면 길이 1리턴
		if (parents[y][0] == x) {
			return 1;
		}
		// x와 y의 깊이 맞추기 -> O(logN)
		for (int i = 19; i >= 0; i--) {
			// x와 y의 깊이 차이 구하기(for문 돌면서)
			if (d[y] - d[x] >= (1 << i)) {
				// 깊이 구하면 y에서 x 깊이로 올라가기 (x의 깊이에 맞는 y의 부모 : parents[y][i])
				y = parents[y][i];
				// 올라간 깊이 cnt에 더해주기
				cnt += (1 << i);
			}
		}
		// 공통조상 찾기
		if (parents[y][0] != parents[x][0]) {
			for (int i = 19; i >= 0; i--) {
				if (parents[x][i] != parents[y][i]) {
					// 올라가는 만큼 cnt에 더하기
					cnt += 2 * (1 << i);
					// 공통조상 찾기 전까지 위로 맞춰서 올라가기
					x = parents[x][i];
					y = parents[y][i];
				}
			}
		}
		// 공통조상을 찾으면
		if (parents[x][0] == parents[y][0]) {
			// 왓다갓다 2번 하는 값 더하기
			return cnt + 2;
		}
		return cnt;
	}

	private static void swap(int prev, int cur) {
		int temp = prev;
		prev = cur;
		cur = temp;
	}

}
