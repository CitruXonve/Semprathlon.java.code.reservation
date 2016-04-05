
/**
 * Apr 5, 2016 3:31:54 PM
 * PrjName: hdu1285
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	static int[] degree;
	static boolean[][] arc;
	static Vector<Integer> ans = new Vector<Integer>();
	static PriorityQueue<Integer> que = new PriorityQueue<Integer>();

	static void Topo(int n) {
		for (int u = 1; u <= n; u++)
			if (degree[u] == 0) {
				que.add(u);
			}
		while (!que.isEmpty()) {
			int u = que.poll();
			ans.add(u);
			for (int v = 1; v <= n; v++) {
				if (arc[u][v]) {
					degree[v]--;
					if (degree[v] == 0) {
						que.add(v);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			degree = new int[n + 1];
			arc = new boolean[n + 1][n + 1];
			for (int i = 0; i < m; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				if (!arc[u][v]) {
					arc[u][v] = true;
					degree[v]++;
				}
			}
			ans.clear();
			Topo(n);

			for (int i = 0; i < n; i++) {
				out.print(ans.get(i));
				if (i < n - 1)
					out.print(" ");
			}
			out.println();
		}
		out.flush();
		out.close();
	}

}

class Edge {
	int v, nx;

	Edge(int _u, int _w) {
		v = _u;
		nx = _w;
	}
}

class Graph {
	int[] h;
	int sz;
	Edge[] edge;

	Graph(int size) {
		sz = size;
		h = new int[sz + 1];
		edge = new Edge[sz + 1];
		Arrays.fill(h, -1);
		h[0] = 0;
	}

	void add(int u, int v) {
		edge[h[0]] = new Edge(v, h[u]);
		h[u] = h[0]++;
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public boolean hasNext() {
		return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
	}

	public String nextLine() {
		String tmp = null;
		try {
			tmp = reader.readLine();
			tokenizer = new StringTokenizer(tmp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			return null;
		}
		return tmp;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}