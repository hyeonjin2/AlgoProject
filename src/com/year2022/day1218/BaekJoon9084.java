package com.year2022.day1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전
public class BaekJoon9084 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[] coins = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int[] money = new int[M + 1];
			money[0] = 1;
			for (int i = 0; i < N; i++) {
				int coin = coins[i];
				for (int j = coin; j <= M; j++) {
					money[j] += money[j - coin];
				}
			}
			sb.append(money[M]).append("\n");
		}
		System.out.println(sb);
	}

}
