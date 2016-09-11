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

	public Dinic(int _n, int _m) {
		maxn = _n;
		maxm = _m;
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
		g.addEdge(u, v, w);
	}

	int maxflow(int source, int target,int n) {
		int ans = 0;
		int[] numh,h,curedge,pre;
		numh=new int[maxn];
		h=new int[maxn];
		curedge=new int[maxn];
		pre=new int[maxn];
		int cut,u,tmp,neck;
		Arrays.fill(h, 0);
		Arrays.fill(numh, 0);
		Arrays.fill(pre, -1);
		for(int i=1;i<=n;i++)
			curedge[i]=g.h[i];
		numh[0]=n;
		u=source;
		while(h[source]<n){
			if (u==target){
				cut=inf;
				for(int i=source;i!=target;edge)
			}
		}
		return ans;
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

	void addEdge(int u, int v, int w) {
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