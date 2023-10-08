package com.year2023.month10.day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 암호문 3
public class SWEA1230 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer> list = new LinkedList<>();

			// 연결 리스트 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.addLast(Integer.parseInt(st.nextToken()));
			}

			// 명령어 입력받기
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				// 삽입
				String order = st.nextToken();
				if (order.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						list.add(x + i, Integer.parseInt(st.nextToken()));
					}
				}
				// 삭제
				else if (order.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						list.remove(x);
					}
				}
				// 추가
				else {
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						list.addLast(Integer.parseInt(st.nextToken()));
					}
				}
			}

			StringBuilder answer = new StringBuilder();
			for (int i = 0; i < 10; i++) {
				answer.append(list.get(i)).append(" ");
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

}
