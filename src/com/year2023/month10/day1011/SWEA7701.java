package com.year2023.month10.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 염라대왕의 이름 정렬
public class SWEA7701 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<String> heap = new PriorityQueue<String>((e1, e2) -> {
				if (e1.length() == e2.length()) {
					return e1.compareTo(e2);
				}
				return e1.length() - e2.length();
			});
			for (int i = 0; i < N; i++) {
				heap.add(br.readLine());
			}

			System.out.printf("#%d\n", tc);
			String prev = "";
			while (!heap.isEmpty()) {
				String now = heap.poll();
				if (!now.equals(prev))
					System.out.println(now);
				prev = now;
			}
		}

	}

}
