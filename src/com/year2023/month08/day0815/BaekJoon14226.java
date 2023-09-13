package com.year2023.month08.day0815;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// �̸�Ƽ��
public class BaekJoon14226 {

	static class Emoticon {
		int screen;
		int clip;
		int time;

		public Emoticon(int screen, int clip, int time) {
			this.screen = screen;
			this.clip = clip;
			this.time = time;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();

		bfs(S);
	}

	private static void bfs(int end) {
		Queue<Emoticon> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[1001][1001];
		queue.offer(new Emoticon(1, 0, 0));
		visited[0][1] = true;

		while (!queue.isEmpty()) {
			Emoticon cur = queue.poll();

			if (cur.screen == end) {
				System.out.println(cur.time);
				return;
			}

			// 1. ȭ�鿡 �ִ� �̸�Ƽ���� ��� �����ؼ� Ŭ�����忡 �����Ѵ�.
			queue.offer(new Emoticon(cur.screen, cur.screen, cur.time + 1));

			// 2. Ŭ�����忡 �ִ� ��� �̸�Ƽ���� ȭ�鿡 �ٿ��ֱ� �Ѵ�.
			if (cur.clip != 0 && cur.screen + cur.clip <= end && !visited[cur.clip][cur.screen + cur.clip]) {
				queue.offer(new Emoticon(cur.screen + cur.clip, cur.clip, cur.time + 1));
				visited[cur.clip][cur.screen + cur.clip] = true;
			}

			// 3. ȭ�鿡 �ִ� �̸�Ƽ�� �� �ϳ��� �����Ѵ�.
			if (cur.screen > 0 && !visited[cur.clip][cur.screen - 1]) {
				queue.offer(new Emoticon(cur.screen - 1, cur.clip, cur.time + 1));
				visited[cur.clip][cur.screen - 1] = true;
			}
		}
	}
}
