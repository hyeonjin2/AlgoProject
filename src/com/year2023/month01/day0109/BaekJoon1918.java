package com.year2023.month01.day0109;

import java.io.IOException;
import java.util.*;

// 후위 표기식
public class BaekJoon1918 {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine().trim();

    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    int bracket = 0;

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      // 문자이면 sb에 추가
      if (ch >= 'A' && ch <= 'Z') {
        sb.append(ch);
      }
      // 연산자이면 stack에 저장
      else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        if (!stack.isEmpty() && (stack.peek() == '/' || stack.peek() == '*')) {
          if (ch == '+' || ch == '-') {
            if (i - 2 > 0 && str.charAt(i - 2) == '(') {
              continue;
            }
            while (!stack.isEmpty()) {
              char op = stack.pop();
              sb.append(op);
            }
          }
        }
        stack.push(ch);
      } else if (ch == ')') {
        while (!stack.isEmpty()) {
          char op = stack.pop();
          sb.append(op);
        }
        bracket--;
      } else if (ch == '(') {
        bracket++;
      }
    }
    while (!stack.isEmpty()) {
      char op = stack.pop();
      sb.append(op);
    }
    System.out.println(sb);
  }
}
