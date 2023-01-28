package com.year2023.day0114;

import java.io.IOException;
import java.util.*;

// 후위 표기식
public class BaekJoon1918 {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine().trim();

    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      // 문자이면 sb에 추가
      if (ch >= 'A' && ch <= 'Z') {
        sb.append(ch);
      }
      // 현재 연산자가 stack의 마지막 연산자보가 우선순위가 낮은 경우 -> stack의 연산자부터 계산하고 현재 연산자 계산
      else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        // 우선순위가 낮은 연산자를 만날 때까지 stack에서 연산자 꺼내기
        while (!stack.isEmpty() && (priority(stack.peek())) >= priority(ch)) {
          sb.append(stack.pop());
        }
        // 현재 연산자 stack에 넣기
        stack.push(ch);
      }
      // ( stack에 넣기
      else if (ch == '(') {
        stack.push(ch);
      }
      // ( 를 만날 때까지 괄호안의 모든 연산자 꺼내기
      else if (ch == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          sb.append(stack.pop());
        }
        // ( stack에서 지우기
        stack.pop();
      }
    }
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    System.out.println(sb);
  }

  // 연산자 우선순위 정하는 메소드
  public static int priority(char op) {
    if (op == '(' || op == ')') {
      return 0;
    } else if (op == '+' || op == '-') {
      return 1;
    } else if (op == '*' || op == '/') {
      return 2;
    }
    return -1;
  }
}
