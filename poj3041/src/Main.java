
/**
 * May 6, 2016 5:10:15 PM
 * PrjName: poj3041
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	static HK work = new HK(1001);

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = in.nextInt();
		int m = in.nextInt();
		work.init(n << 1, n, n);
		for (int i = 0; i < m; i++) {
			work.addEdge(in.nextInt() - 1, in.nextInt() + n - 1);
		}
		out.println(work.maxmatch());
		out.flush();
		out.close();
	}
}

class HK {
	private int inf = 0x3fffffff;
	private int n, n1, n2, maxn;
	private Vector<Integer>[] vec;
	private int[] mx, my, dx, dy;
	private boolean[] vis;
	private Queue<Integer> que;
	private int dis;

	public HK(int _n) {
		maxn = _n;
		vec = new Vector[maxn];
		for (int i = 0; i < maxn; i++)
			vec[i] = new Vector<Integer>();
		mx = new int[maxn];
		my = new int[maxn];
		vis = new boolean[maxn];
		que = new LinkedList<Integer>();
		dx = new int[maxn];
		dy = new int[maxn];
	}

	void init(int _n, int _n1, int _n2) {
		n = _n;	n1 = _n1;	n2 = _n2;
		for (int i = 0; i < n; i++)
			vec[i].clear();

	}

	void addEdge(int u, int v) {
		vec[u].add(v);
		vec[v].add(u);
	}

	boolean searchpath() {
		que.clear();
		dis = inf;
		Arrays.fill(dx, -1);
		Arrays.fill(dy, -1);
		for (int i = 0; i < n1; i++) {
			if (mx[i] == -1) {
				que.add(i);
				dx[i] = 0;
			}
		}
		while (!que.isEmpty()) {
			int u = que.poll();
			if (dx[u] > dis)
				break;
			for (int i = 0; i < vec[u].size(); i++) {
				int v = vec[u].get(i);
				if (dy[v] == -1) {
					dy[v] = dx[u] + 1;
					if (my[v] == -1)
						dis = dy[v];
					else {
						dx[my[v]] = dy[v] + 1;
						que.add(my[v]);
					}
				}
			}
		}
		return dis != inf;
	}

	boolean findpath(int u) {
		for (int i = 0; i < vec[u].size(); i++) {
			int v = vec[u].get(i);
			if (!vis[v] && dy[v] == dx[u] + 1) {
				vis[v] = true;
				if (my[v] != -1 && dy[v] == dis)
					continue;
				if (my[v] == -1 || findpath(my[v])) {
					my[v] = u;
					mx[u] = v;
					return true;
				}
			}
		}
		return false;
	}

	int maxmatch() {
		int res = 0;
		Arrays.fill(mx, -1);
		Arrays.fill(my, -1);
		while (searchpath()) {
			Arrays.fill(vis, false);
			for (int i = 0; i < n1; i++)
				if (mx[i] == -1)
					res += findpath(i) ? 1 : 0;
		}
		return res;
	}
}

/*
 * class Hungary { private int n, maxn; private Vector<Integer>[] vec; private
 * int[] match; private boolean[] vis;
 * 
 * public Hungary(int _n) { maxn = _n; vec = new Vector[maxn]; for (int i = 0; i
 * < maxn; i++) vec[i] = new Vector<Integer>(); match = new int[maxn]; vis = new
 * boolean[maxn]; }
 * 
 * void init(int _n) { n = _n; for (int i = 0; i < n; i++) vec[i].clear();
 * 
 * }
 * 
 * void addEdge(int u, int v) { vec[u].add(v); vec[v].add(u); }
 * 
 * boolean dfs(int v) { vis[v] = true; for (int i = 0; i < vec[v].size(); i++) {
 * int u = vec[v].get(i); int w = match[u]; if (w < 0 || !vis[w] && dfs(w)) {
 * match[v] = u; match[u] = v; return true; } } return false; }
 * 
 * int matching() { int res = 0; Arrays.fill(match, -1); for (int i = 0; i < n;
 * i++) if (match[i] < 0) { Arrays.fill(vis, false); if (dfs(i)) res++; } return
 * res; } }
 */

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