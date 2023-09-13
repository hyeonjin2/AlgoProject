package com.year2023.month07.day0704;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon16929_array {

	static int N, M, answer;

	// ��ٸ�, �� ������ ���� �迭 (�ϳ��� ĭ�� ��ٸ��� ���� ���ÿ� ���� �� �����Ƿ� �ϳ��� �迭�� ����
	static int[] move;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		move = new int[101];

		// ��ٸ� ���� ����
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			move[x] = y;
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
				
				// ��ٸ� �Ǵ� ���� �ִ� ĭ�� �����ߴ��� Ȯ��
				if(move[next]!=0) {
					next = move[next];
					
				}
				
				if (!visited[next]) {
					queue.offer(new Point(next, count + 1));
					visited[next] = true;
				}
			}
		}
	}

}
