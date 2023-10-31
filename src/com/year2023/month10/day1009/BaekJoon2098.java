package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 외판원 순회
public class BaekJoon2098 {

	static final int INF = 16_000_000;
	static int N, visitAll;
	static int[][] w, d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		w = new int[N][N];
		visitAll = (1 << N) - 1;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// d[i][j] : i(이진수)들의 도시들을 거쳐서 j로 올 때의 최소 비용
		// d[11][2] = 1->2 = 15
		// d[111][3] = 1->2->3 or 2->1->3 => min(d[11][2]+w[2][3],d[11][1]+w[1][3])
		d = new int[N][visitAll];
		for (int i = 0; i < N; i++) {
			Arrays.fill(d[i], -1);
		}
		// 전체 도시를 순회하는 경우, 어디서 출발하든지 최소비용은 동일 -> 편의상 1번 도시를 시작도시로 지정
		System.out.println(tsp(0, 1)); 
	}

	static int tsp(int city, int visit) {
		// 모든 도시를 방문했을 때
		if (visit == visitAll) {
			// city에서 1번 도시로 가는 길이 없으면 INF 리턴
			if (w[city][0] == 0)
				return INF;
			return w[city][0];
		}

		// 이미 방문한 도시라면 저장된 값 리턴
		if (d[city][visit] != -1)
			return d[city][visit];

		// 도시 방문 체크
		d[city][visit] = INF;

		// 방문하지 않은 다른 도시 확인
		for (int i = 0; i < N; i++) {
			// next : i 도시 방문
			int next = visit | (1 << i);

			// 경로가 없거나 이미 방문한 경우 continue
			if (w[city][i] == 0 || (visit & (1 << i)) != 0)
				continue;

			d[city][visit] = Math.min(d[city][visit], tsp(i, next) + w[city][i]);
		}

		return d[city][visit];
	}
}
