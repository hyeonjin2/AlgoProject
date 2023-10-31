package com.year2023.month10.day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최장 공통 부분 수열
public class SWEA3304 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken();
			String str2 = st.nextToken();

			// d[i][j] : str1의 i번째와 str2의 j번째 까지의 공통된 부분 수열의 최대 길이
			// d[i][j] => d[i-1][j-1] or d[i-1][j] or d[i][j-1]에서 오는 경우
			// d[i][j] = d[i-1][j-1]+1 -> str1의 i번째 문자와 str2의 j번째 문자가 같을 때
			// str1의 마지막 문자가 str2의 마지막 문자가 다를 때 -> max(d[i-1][j],d[i][j-1])
			int[][] d = new int[str1.length() + 1][str2.length() + 1];
			for (int i = 1; i <= str1.length(); i++) {
				for (int j = 1; j <= str2.length(); j++) {
					if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
						d[i][j] = d[i - 1][j - 1] + 1;
					} else {
						d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
					}
				}
			}
			System.out.println("#" + tc + " " + d[str1.length()][str2.length()]);
		}
	}

}
