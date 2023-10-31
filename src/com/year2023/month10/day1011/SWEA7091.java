package com.year2023.month10.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 은기의 아주 큰 그림
public class SWEA7091 {

	final static int HASH_SIZE = (1 << 30) - 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] targetArr = new int[H][W];
			int[][] originArr = new int[N][M];

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					targetArr[i][j] = str.charAt(j) == 'o' ? 1 : 0;
				}
			}

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					originArr[i][j] = str.charAt(j) == 'o' ? 1 : 0;
				}
			}

			int result = solution(targetArr, originArr);
			System.out.printf("#%d %d\n", tc, result);
		}
	}

	private static int solution(int[][] targetArr, int[][] originArr) {
		int H = targetArr.length;
		int W = targetArr[0].length;
		int N = originArr.length;
		int M = originArr[0].length;

		// 패턴에 대한 hash값 구하기
		int hashVal = getHashArr(targetArr, H, W)[0][0];
		int[][] hashArr = getHashArr(originArr, H, W);
		int cnt = 0;
		for (int i = 0; i <= N - H; i++) {
			for (int j = 0; j <= M - W; j++) {
				cnt = hashArr[i][j] == hashVal ? cnt + 1 : cnt;
			}
		}

		return cnt;
	}

	private static int[][] getHashArr(int[][] matrix, int height, int width) {
		int H = matrix.length;
		int W = matrix[0].length;
		int[][] horizonHashArr = new int[H][W - width + 1];
		int rowMaxP = getMaxPower(height, 5);
		int colMaxP = getMaxPower(width, 4);

		// horizontal 압축
		for (int i = 0; i < H; i++) {
			int hash = getHorizonHash(matrix, width, i, 0);
			horizonHashArr[i][0] = hash;
			for (int j = 1; j <= W - width; j++) {
				horizonHashArr[i][j] = getNext(hash, matrix[i][j - 1], colMaxP, matrix[i][j - 1 + width], 4);
				hash = horizonHashArr[i][j];
			}
		}

		// vertinal 압축
		int[][] verticalHashArr = new int[H - height + 1][W - width + 1];
		for (int j = 0; j <= W - width; j++) {
			int hash = getVerticalHash(horizonHashArr, height, 0, j);
			verticalHashArr[0][j] = hash;
			for (int i = 1; i <= H - height; i++) {
				verticalHashArr[i][j] = getNext(hash, horizonHashArr[i - 1][j], rowMaxP,
						horizonHashArr[i - 1 + height][j], 5);
				hash = verticalHashArr[i][j];
			}
		}

		return verticalHashArr;
	}

	private static int getMaxPower(int length, int shift) {
		long result = 1;
		for (int i = 0; i < length - 1; i++) {
			result = (result << shift) + result;
		}
		return (int) (result & HASH_SIZE);
	}

	private static int getHorizonHash(int[][] matrix, int length, int row, int col) {
		long result = 0;
		for (int i = 0; i < length; i++) {
			result = (result << 4) + result + matrix[row][col + i]; // * 17 -> 17진법 사용
		}
		return (int) (result & HASH_SIZE);
	}

	private static int getVerticalHash(int[][] matrix, int length, int row, int col) {
		long result = 0;
		for (int i = 0; i < length; i++) {
			result = (result << 5) + result + matrix[row + i][col];
		}
		return (int) (result & HASH_SIZE);
	}

	private static int getNext(int prev, int delete, int maxPower, int add, int shift) {
		long result = prev - (delete * maxPower);
		result = (result << shift) + result + add;
		return (int) (result & HASH_SIZE);
	}

}
