package com.day0119;

// 유사 칸토어 비트열
public class ProgrammersKantor {
  public static void main(String[] args) {
    int result = solution(2, 4, 17);
    System.out.println(result);
  }

  static public int solution(int n, long l, long r) {
    int answer = 0;
    double root = 1.0 / n;
    int min = (int) Math.floor(Math.pow(l, root));
    int max = (int) Math.ceil(Math.pow(r, root));
    for (int i = min; i <= max; i++) {

    }
    return answer;
  }
}
