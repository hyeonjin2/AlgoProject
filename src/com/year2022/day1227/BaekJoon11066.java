package com.year2022.day1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//파일 합치기
public class BaekJoon11066 {
    static int[] files;
    static int[] sum;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());

            files = new int[K + 1];
            sum = new int[K + 1];
            D = new int[K + 1][K + 1]; //D[i][j] : i번째 인덱스에서 j번째 인덱스의 파일을 합치는데 드는 최소 비용

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + files[i];
            }
            //D[i][i+1] = files[i]+files[i+1]
            for (int i = 0; i < K; i++) {
                D[i][i + 1] = files[i] + files[i + 1];
            }
            //D[i][j] = Math.min(D[i][k]+D[k+1][j]+i~j까지 부분합,D[i][j])
            for (int gap = 2; gap <= K; gap++) {
                for (int from = 1; from + gap <= K; from++) {
                    int to = from + gap;
                    D[from][to] = calc(from, to);
                }
            }
            sb.append(D[1][K]).append("\n");
        }
        System.out.println(sb);
    }

    private static int calc(int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            min = Math.min(min, D[i][k] + D[k + 1][j] + sum[j] - sum[i - 1]);
        }
        return min;
    }
}
