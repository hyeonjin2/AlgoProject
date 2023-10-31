package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 중간값 구하기
public class SWEA3000 {

	static final int MOD = 20171109;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((e1, e2) -> e1 - e2);
			PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((e1, e2) -> e2 - e1);

			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			minHeap.add(A);
			int result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				// 큰 값은 minHeap에, 작은 값은 maxHeap에 넣어서 관리
				if (X > Y) {
					minHeap.add(X);
					maxHeap.add(Y);
				} else {
					minHeap.add(Y);
					maxHeap.add(X);
				}

				// 만약 minHeap의 최댓값이 maxHeap의 최솟값보다 작으면 -> 값의 크기가 뒤바뀌면
				while (minHeap.peek() < maxHeap.peek()) {
					// 관리되는 Heap 변경
					int minValue = minHeap.poll();
					int maxValue = maxHeap.poll();
					minHeap.add(maxValue);
					maxHeap.add(minValue);
				}

				// minHeap에 저장된 숫자가 항상 1개 많으므로(A를 minHeap에 넣었으므로)
				// minHeap의 최댓값이 항상 중간값
				result = (minHeap.peek() + result) % MOD;
			}

			System.out.println("#" + tc + " " + result);
		}
	}

}
