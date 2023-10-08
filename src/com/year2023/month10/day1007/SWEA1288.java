package com.year2023.month10.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 새로운 불면증 치료법
public class SWEA1288 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int flag = 0;
			int count = 1;

			while (flag != (Math.pow(2, 10) - 1)) {
				int temp = count * N;
				flag = checkNum(flag, temp);
				count++;
			}

			int ans = N * (count - 1);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static int checkNum(int flag, int num) {
		while (num / 10 != 0) {
			int temp = num % 10;
			flag = flag | (1 << temp);
			num = num / 10;
		}
		flag = flag | (1 << num);
		return flag;
	}

}
