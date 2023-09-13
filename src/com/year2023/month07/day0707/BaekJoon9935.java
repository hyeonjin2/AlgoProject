package com.year2023.month07.day0707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 문자열 폭발
public class BaekJoon9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String bomb = br.readLine();

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			stack.push(input.charAt(i));

			// 만약에 stack의 크키가 폭발 문자열의 크기보다 크거나 같으면
			if (stack.size() >= bomb.length()) {
				// stack에 폭발 문자열과 같은 문자열이 있는지 확인
				boolean isSame = true;
				for (int j = 0; j < bomb.length(); j++) {
					// 문자 하나하나 같은지 차례대로 체크
					if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
						// 중간에 문자가 하나라도 다르면 문자가 폭발할 수 없음 -> break로 for문 탈출
						isSame = false;
						break;
					}
				}
				// 만약 문자열이 같다면, 문자열 stack에서 지우기
				if (isSame) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		// stack에 남아있는 문자열 출력
		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (char ch : stack) {
				sb.append(ch);
			}
			System.out.println(sb);
		}
	}

}
