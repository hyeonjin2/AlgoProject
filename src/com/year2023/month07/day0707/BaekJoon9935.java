package com.year2023.month07.day0707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// ���ڿ� ����
public class BaekJoon9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String bomb = br.readLine();

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			stack.push(input.charAt(i));

			// ���࿡ stack�� ũŰ�� ���� ���ڿ��� ũ�⺸�� ũ�ų� ������
			if (stack.size() >= bomb.length()) {
				// stack�� ���� ���ڿ��� ���� ���ڿ��� �ִ��� Ȯ��
				boolean isSame = true;
				for (int j = 0; j < bomb.length(); j++) {
					// ���� �ϳ��ϳ� ������ ���ʴ�� üũ
					if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
						// �߰��� ���ڰ� �ϳ��� �ٸ��� ���ڰ� ������ �� ���� -> break�� for�� Ż��
						isSame = false;
						break;
					}
				}
				// ���� ���ڿ��� ���ٸ�, ���ڿ� stack���� �����
				if (isSame) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		// stack�� �����ִ� ���ڿ� ���
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
