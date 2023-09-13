package com.year2023.month08.day0823;

import java.util.Scanner;

// 문서 검색
public class BaekJoon1543_replace {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String document = sc.nextLine();
		String word = sc.nextLine();

		// document에서 word를 ""로 대체 -> 삭제
		String replaced = document.replace(word, "");
		// 전체 길이 - 남은 길이 => 반복된 word의 길이
		int length = document.length() - replaced.length();
		// 반복된 word의 길이 / word의 길이 => 반복된 횟수
		int count = length / word.length();
		System.out.println(count);
	}

}
