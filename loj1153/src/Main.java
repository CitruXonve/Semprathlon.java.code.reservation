
/**
 * May 5, 2016 5:31:43 PM
 * PrjName: loj1153
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	final static int maxm = 15010, maxn = 210;
	final static double eps = 1e-3;

	// final static int inf=0x3fffffff;
	static int dcmp(double d) {
		return (d > eps ? 1 : 0) - (d < -eps ? 1 : 0);
	}

	static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	static Dinic dinic;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		dinic = new Dinic(maxn, maxm);
		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			int n = in.nextInt();
			int s = in.nextInt();
			int t = in.nextInt();
			int m = in.nextInt();
			dinic.init();
			for (int i = 0; i < m; i++) {
				dinic.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
			}
			out.println("Case " + (++cas) + ": " + dinic.maxflow(s, t));
		}
		out.flush();
		out.close();
	}

}

class Dinic {
	final int inf = 0x3fffffff;

	private int maxm, maxn;
	public Graph g;
	private int[] dist;
	private boolean[] vis;
	private Queue<Integer> que;

	public Dinic(int _n, int _m) {
		maxn = _n;
		maxm = _m;
		que = new LinkedList<Integer>();
		dist = new int[maxn];
		vis = new boolean[maxn];
	}

	void init() {
		if (g == null)
			g = new Graph(maxn, maxm);
		g.init();
	}

	void init(Graph _g) {
		g = _g;
	}

	void addEdge(int u, int v, int w) {
		g.addedge(u, v, w);
	}

	boolean bfs(int s, int t) {
		que.clear();
		Arrays.fill(dist, -1);
		Arrays.fill(vis, false);
		dist[s] = 0;
		que.add(s);
		vis[s] = true;
		while (!que.isEmpty()) {
			int u = que.poll();
			for (int i = g.h[u]; i != -1; i = g.e[i].next) {
				Edge E = g.e[i];
				if (!vis[E.to] && E.cap > E.flow) {
					dist[E.to] = dist[u] + 1;
					if (E.to == t)
						return true;
					vis[E.to] = true;
					que.add(E.to);
				}
			}
		}
		return false;
	}

	int dfs(int u, int delta, int target) {
		if (u == target || delta == 0)
			return delta;
		int flow = 0, f;
		for (int i = g.h[u]; i != -1; i = g.e[i].next) {
			Edge E = g.e[i];
			if (dist[E.to] == dist[u] + 1 && (f = dfs(E.to, Math.min(delta, E.cap - E.flow), target)) > 0) {
				g.e[i].flow += f;
				g.e[i ^ 1].flow -= f;
				flow += f;
				delta -= f;
				if (delta == 0)
					break;
			}
		}
		return flow;
	}

	int maxflow(int source, int target) {
		int flow = 0;
		while (bfs(source, target)) {
			flow += dfs(source, inf, target);
		}
		return flow;
	}
};

class Edge {
	int to, cap, flow, next;

	Edge() {
	}

	Edge(int _v, int _f, int _w, int _nx) {
		modify(_v, _f, _w, _nx);
	}

	void modify(int _v, int _f, int _w, int _nx) {
		to = _v;
		cap = _f;
		flow = _w;
		next = _nx;
	}
};

class Graph {
	int[] h;
	int maxn, maxm;
	Edge[] e;
	int num;

	Graph(int _n, int _m) {
		maxn = _n;
		maxm = _m;
		h = new int[maxn];
		e = new Edge[maxm];
		for (int i = 0; i < maxm; i++)
			e[i] = new Edge();
	}

	void init() {
		Arrays.fill(h, -1);
		num = 0;
	}

	void addedge(int u, int v, int w) {
		e[num].modify(v, w, 0, h[u]);
		h[u] = num++;
		e[num].modify(u, w, 0, h[v]);
		h[v] = num++;
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