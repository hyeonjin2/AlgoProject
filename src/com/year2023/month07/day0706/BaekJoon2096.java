package com.year2023.month07.day0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내려가기
public class BaekJoon2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] max = new int[3];
		int[] min = new int[3];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int f0 = Integer.parseInt(st.nextToken());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());

			if (i == 0) {
				max[0] = f0;
				max[1] = f1;
				max[2] = f2;

				min[0] = f0;
				min[1] = f1;
				min[2] = f2;

				continue;
			}
			int preMax0 = max[0];
			int preMax2 = max[2];
			max[0] = Math.max(max[0], max[1]) + f0;
			max[2] = Math.max(max[1], max[2]) + f2;
			max[1] = Math.max(preMax0, Math.max(max[1], preMax2)) + f1;

			int preMin0 = min[0];
			int preMin2 = min[2];
			min[0] = Math.min(min[0], min[1]) + f0;
			min[2] = Math.min(min[1], min[2]) + f2;
			min[1] = Math.min(preMin0, Math.min(min[1], preMin2)) + f1;
		}

		int ans1 = Math.max(max[0], Math.max(max[1], max[2]));
		int ans2 = Math.min(min[0], Math.min(min[1], min[2]));

		System.out.println(ans1 + " " + ans2);
	}

}
