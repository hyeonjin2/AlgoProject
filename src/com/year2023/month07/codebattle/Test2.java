package com.year2023.month07.codebattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Test2 {

	static int N, M, Q;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0;

			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());

				map[r - 1][c - 1] = x;
				int safe = calc();
				ans += safe;
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static int calc() {
		// ������ ����
		Integer[] maxRow = new Integer[N];

		for (int i = 0; i < N; i++) {
			maxRow[i] = Arrays.stream(map[i]).max().orElse(0);
		}
		// ������ ����
		Integer[] maxCol = new Integer[M];

		for (int j = 0; j < M; j++) {
			int max = 0;
			for (int i = 0; i < N; i++) {
				max = Math.max(max, map[i][j]);
			}
			maxCol[j] = max;
		}

		int safe = 0;
		Arrays.sort(maxRow, Collections.reverseOrder());
		Arrays.sort(maxCol, Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maxRow[i].equals(maxCol[j])) {
					safe++;
					break; // �� �̻� ���� ������ ���� ������ �� ���� -> �ٷ� ���� �� �˻��Ϸ� ����
				} else if (maxRow[i] > maxCol[j]) {
					break;
				}
			}
		}

		return safe;
	}

}
