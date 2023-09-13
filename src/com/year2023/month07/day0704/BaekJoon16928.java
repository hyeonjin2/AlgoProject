package com.year2023.month07.day0704;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀과 사다리 게임
public class BaekJoon16928 {

	static int N, M, answer;

	// 사다리, 뱀 정보를 담을 배열 (하나의 칸이 사다리와 뱀을 동시에 가질 수 없으므로 하나의 배열로 관리
	static List<Point> move;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		move = new ArrayList<>();

		// 사다리 정보 저장
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Point p = new Point(x, y);
			move.add(p);
		}

		bfs();

		System.out.println(answer);
	}

	static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		queue.offer(new Point(1, 0));
		visited[1] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int location = cur.x;
			int count = cur.y;
			if (location == 100) {
				answer = count;
				break;
			}
			for (int i = 1; i <= 6; i++) {
				int next = location + i;

				if (next > 100)
					continue;
				if (next == 100) {
					queue.offer(new Point(next, count + 1));
					break;
				}
				// 사다리 또는 뱀이 있는 칸에 도착했는지 확인
				for (Point p : move) {
					// 사다리 또는 뱀이 있는 칸이라면 이동
					if (next == p.x) {
						next = p.y;
					}
				}
				if (!visited[next]) {
					queue.offer(new Point(next, count + 1));
					visited[next] = true;
				}
			}
		}
	}

}
