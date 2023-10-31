package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 힙
public class SWEA2930_PQ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 연산 횟수
			PriorityQueue<Integer> heap = new PriorityQueue<>((e1, e2) -> e2 - e1);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int operator = Integer.parseInt(st.nextToken());
				switch (operator) {
				// 삽입
				case 1:
					int num = Integer.parseInt(st.nextToken());
					heap.offer(num);
					break;
				// 삭제
				case 2:
					if (heap.isEmpty())
						sb.append("-1").append(" ");
					else
						sb.append(heap.poll()).append(" ");
					break;
				default:
					break;
				}
			}
			System.out.println("#" + tc + " " + sb);
		}

	}

}
