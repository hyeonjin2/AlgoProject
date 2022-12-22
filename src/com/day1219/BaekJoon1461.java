package com.day1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도서관
public class BaekJoon1461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 큰 수가 앞으로 오도록 정렬
		PriorityQueue<Integer> pQ = new PriorityQueue<>((e1, e2) -> e2 - e1);
		PriorityQueue<Integer> nQ = new PriorityQueue<>((e1, e2) -> e2 - e1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if (temp > 0) {
				pQ.offer(temp);
			} else {
				nQ.offer(Math.abs(temp));
			}
		}

		// 가장 멀리 있는 책의 위치 더하기
		int maxD = 0;
		// 모두 음수일 경우
		if (pQ.isEmpty()) {
			maxD = nQ.peek();
		}
		// 모두 양수일 경우
		else if (nQ.isEmpty()) {
			maxD = pQ.peek();
		}
		// 음수, 양수 골고루 있을 경우
		else {
			maxD = Math.max(pQ.peek(), nQ.peek());
		}

		int ans = 0;

		while (!pQ.isEmpty()) {
			int temp = pQ.poll();
			for (int i = 0; i < M - 1; i++) {
				pQ.poll();

				if (pQ.isEmpty()) {
					break;
				}
			}
			ans += temp * 2;
		}
		while (!nQ.isEmpty()) {
			int temp = nQ.poll();
			for (int i = 0; i < M - 1; i++) {
				nQ.poll();

				if (nQ.isEmpty()) {
					break;
				}
			}
			ans += temp * 2;
		}
		ans -= maxD;
		System.out.println(ans);
	}

}
