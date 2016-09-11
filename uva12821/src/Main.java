
/**
 * May 4, 2016 2:22:05 PM
 * PrjName: uva12821
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	static Graph g;
	static MinCostMaxFlow flow;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int cas = 0;
		while (in.hasNext()) {
			int n = in.nextInt(), m = in.nextInt();
			g = new Graph((n << 2), (m << 3));
			g.addedge(1, 2, 2, 0);
			g.addedge(n + 1, (n + 1) << 1 | 1, 2, 0);
			for (int i = 0; i < m; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int d = in.nextInt();
				int a = in.nextInt();
				g.addedge(u + 1, u + n + 1, 2, 0);
				g.addedge(u + n + 1, v + 1, 1, d);
				g.addedge(u + n + 1, v + 1, 1, d + a);
			}
			flow = new MinCostMaxFlow(g, (n << 2), m << 2, 1, (n + 1) << 1 | 1);
			out.println("Case " + (++cas) + ": " + flow.solve());
		}
		out.flush();
		out.close();
	}

}

class Edge {
	int v, f, w, nx;

	Edge(int _v, int _f, int _w, int _nx) {
		v = _v;
		f = _f;
		w = _w;
		nx = _nx;
	}
}

class Graph {
	int[] h;
	int n, m;
	Edge[] e;

	Graph(int _n, int _m) {
		n = _n;
		m = _m;
		h = new int[n];
		Arrays.fill(h, -1);
		h[0] = 0;
		e = new Edge[m];
	}

	void addedge(int u, int v, int f, int w) {
		e[h[0]] = new Edge(v, f, w, h[u]);
		h[u] = h[0]++;
		e[h[0]] = new Edge(u, 0, -w, h[v]);
		h[v] = h[0]++;
	}
}

class MinCostMaxFlow {
	final int inf = 0x3fffffff;
	int n, m;
	Graph g;
	spfa work;
	int num, src, sink;

	public MinCostMaxFlow(Graph _g, int _n, int _m, int _u, int _v) {
		n = _n;
		m = _m;
		g = _g;
		src = _u;
		sink = _v;
	}

	boolean findPath() {
		work = new spfa(g, n, src, sink);
		return work.get_res(sink) < inf;
	}

	int augment() {
		int u = sink;
		int delta = inf;
		while (u != src) {
			if (g.e[work.pree[u]].f < delta)
				delta = g.e[work.pree[u]].f;
			u = work.prev[u];
		}
		u = sink;
		while (u != src) {
			g.e[work.pree[u]].f -= delta;
			g.e[work.pree[u] ^ 1].f += delta;
			u = work.prev[u];
		}
		return work.dis[sink] * delta;
	}

	int solve() {
		int cost = 0, flow = 0;
		while (findPath()) {
			int u = sink;
			int delta = inf;
			while (u != src) {
				delta = Math.min(delta, g.e[work.pree[u]].f);
				u = work.prev[u];
			}
			u = sink;
			while (u != src) {
				g.e[work.pree[u]].f -= delta;
				g.e[work.pree[u] ^ 1].f += delta;
				u = work.prev[u];
			}
			cost += work.dis[sink] * delta;
			flow += delta;
		}
		return cost;
	}
}

class spfa {
	final int inf = 0x3fffffff;
	int maxn, src, sink;
	Graph g;
	Queue<Integer> que;
	boolean[] inQue;
	int[] dis, prev, pree;

	public spfa(Graph _g, int _n, int _u, int _v) {
		g = _g;
		maxn = _n;
		src = _u;
		sink = _v;
		dis = new int[maxn];
		inQue = new boolean[maxn];
		prev = new int[maxn];
		pree = new int[maxn];
	}

	void solve() {
		que = new LinkedList<Integer>();
		que.add(src);
		Arrays.fill(dis, inf);
		dis[src] = 0;
		inQue[src] = true;
		while (!que.isEmpty()) {
			int u = que.poll();
			for (int i = g.h[u]; i != -1; i = g.e[i].nx) {
				if (g.e[i].f > 0 && dis[u] + g.e[i].w < dis[g.e[i].v]) {
					dis[g.e[i].v] = dis[u] + g.e[i].w;
					prev[g.e[i].v] = u;
					pree[g.e[i].v] = i;
					if (!inQue[g.e[i].v]) {
						inQue[g.e[i].v] = true;
						que.add(g.e[i].v);
					}
				}
			}
			inQue[u] = false;
		}
	}

	int get_res(int v) {
		solve();
		return dis[v];
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