package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0/1 Knapsack
public class SWEA3282 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] volume = new int[N + 1];
			int[] cost = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				volume[i] = Integer.parseInt(st.nextToken());
				cost[i] = Integer.parseInt(st.nextToken());
			}

			// d[i][j] : 1~i번째 물건을 사용해서 무게 j가방을 채울 수 있는 최대 가치
			// d[i][j] = d[i-1][j-i무게] + i값
			int[][] d = new int[N + 1][K + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j <= K; j++) {
					d[i][j] = d[i - 1][j];
					if (j - volume[i] >= 0) {
						d[i][j] = Math.max(d[i][j], d[i - 1][j - volume[i]] + cost[i]);
					}
				}
			}

			System.out.println("#" + tc + " " + d[N][K]);
		}
	}

}
