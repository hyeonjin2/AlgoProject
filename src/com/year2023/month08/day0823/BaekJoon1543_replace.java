package com.year2023.month08.day0823;

import java.util.Scanner;

public class BaekJoon1543_replace {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String document = sc.nextLine();
		String word = sc.nextLine();

		// word�� �ش��ϴ� ���ڵ� ""�� ġȯ -> ����
		String replaced = document.replace(word, "");
		// ���� ���� - ���� ���� => ������ ������ ����
		int length = document.length() - replaced.length();
		// ������ ������ ���� / word�� ���� => ������ ������ ��
		int count = length / word.length();
		System.out.println(count);
	}

}
