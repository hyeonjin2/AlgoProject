package com.year2023.month10.day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트리 순회
public class BaekJoon1991 {

	static class Node {
		char info;

		Node left, right;

		public Node(char info) {
			this.info = info;
		}

		public void insertNode(char info, char left, char right) {
			// 입력으로 들어온 info와 같은 노드라면
			if (this.info == info) {
				// 왼쪽 노드가 존재하는 경우 왼쪽 노드 할당
				if (left != '.') {
					this.left = new Node(left);
				}
				// 오른쪽 노드가 존재하는 경우 오른쪽 노드 할당
				if (right != '.') {
					this.right = new Node(right);
				}
			}
			// 입력으로 들어온 info와 같은 노드를 찾기 위해 재귀 호출
			else {
				// 왼쪽 노드가 존재한다면 왼쪽 노드쪽으로 info 찾기 시작
				if (this.left != null) {
					this.left.insertNode(info, left, right);
				}
				// 오른쪽 노드가 존재한다면 오른쪽 노드쪽으로 info 찾기 시작
				if (this.right != null) {
					this.right.insertNode(info, left, right);
				}
			}
		}

	}

	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		// root 노드 생성
		char rootInfo = st.nextToken().charAt(0);
		Node root = new Node(rootInfo);
		root.insertNode(rootInfo, st.nextToken().charAt(0), st.nextToken().charAt(0));

		// 트리 완성하기
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char info = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			root.insertNode(info, left, right);
		}

		// 출력
		sb = new StringBuilder();
		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);

		System.out.println(sb);

	}

	private static void preOrder(Node node) {
		if (node == null)
			return;
		sb.append(node.info);
		preOrder(node.left);
		preOrder(node.right);
	}

	private static void inOrder(Node node) {
		if (node == null)
			return;
		inOrder(node.left);
		sb.append(node.info);
		inOrder(node.right);
	}

	private static void postOrder(Node node) {
		if (node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.info);
	}
}
