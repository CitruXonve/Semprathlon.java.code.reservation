import java.io.*;
import java.util.*;

public class Main {
	static int[] x, y, f;
	static Vector<Edge> v = new Vector<Edge>();
	static Edge[] E;

	static int getf(int x) {
		if (f[x] == x)
			return x;
		f[x] = getf(f[x]);
		return f[x];
	}

	static boolean unite(int x, int y) {
		int a = getf(x);
		int b = getf(y);
		if (a == b)
			return false;
		f[a] = b;
		return true;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			x = new int[n];
			y = new int[n];
			f = new int[n + 1];
			for (int i = 0; i < n; i++) {
				x[i] = in.nextInt();
				y[i] = in.nextInt();
			}
			v.clear();
			for (int i = 0; i < n; i++)
				for (int j = i + 1; j < n; j++) {
					double d = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
					v.add(new Edge(i + 1, j + 1, d));
				}
			E = v.toArray(new Edge[0]);
			Arrays.sort(E, new Edge.Comp());

			double ans = 0x7fffffff;
			for (int k = 1; k <= n; k++) {
				int cnt = 0;
				double tmp = 0;
				for (int i = 1; i <= n; i++)
					f[i] = i;
				for (int i = 0; i < E.length && cnt < n - 2 ; i++) {
					Edge e = E[i];
					if (e.u == k || e.v == k)
						continue;
					if (unite(e.u, e.v)) {
						cnt++;
						tmp += e.d;
					} else
						continue;
				}
				ans = Math.min(ans, tmp);
			}

			out.println(String.format("%.2f", ans));
		}
		out.flush();
		out.close();
	}

}

class Edge {
	int u, v;
	double d;
	final static double eps = 1e-6;

	Edge() {
	}

	Edge(int _u, int _v, double _d) {
		u = _u;
		v = _v;
		d = _d;
	}

	static class Comp implements Comparator<Edge> {
		public int compare(Edge e1, Edge e2) {
			if (Math.abs(e1.d - e2.d) <= eps)
				return 0;
			else
				return Double.compare(e1.d, e2.d);
		}
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
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
