package poj3253;

import java.io.*;

class Heap {
	private final int maxn = 50010;
	int[] data;
	int r;

	Heap() {
		data = new int[maxn];
		r = 0;
	}

	public int size() {
		return r;
	}

	void swap(int a, int b) {
		int tmp = data[a];
		data[a] = data[b];
		data[b] = tmp;
	}

	void up(int p) {
		if (!(p > 0))
			return;
		int q = p >> 1;
		if (data[p] < data[q]) {
			swap(p, q);
			up(q);
		}
	}

	void down(int p) {
		int q;
		if ((p << 1) >= r)
			return;
		else if ((p << 1) == r - 1) {
			q = p << 1;
		} else {
			q = (data[p << 1] < data[p << 1 | 1] ? p << 1 : p << 1 | 1);
		}
		if (data[p] > data[q]) {
			swap(p, q);
			down(q);
		}
	}

	void push(int n) {
		data[r++] = n;
		up(r - 1);
	}

	int pop() {
		int res = data[0];
		swap(0, r - 1);
		r--;
		down(0);
		return res;
	}

	int top() {
		return data[0];
	}

}

public class Main {
	private static long solve(int[] a) {
		Heap hp = new Heap();
		int n = a[0], l1, l2;
		long res = 0;
		for (int i = 1; i <= n; i++)
			hp.push(a[i]);
		// hp.print();
		while (hp.size() > 1) {
			l1 = hp.pop();
			l2 = hp.pop();
			res += (long) (l1 + l2);
			hp.push(l1 + l2);
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(System.out);
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			int[] a = new int[n + 1];
			a[0] = n;
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				a[i] = (int) in.nval;
			}

			if (n > 1)
				out.println(solve(a));
			else
				out.println(a[1]);
			out.flush();
		}
		out.close();
	}
}
