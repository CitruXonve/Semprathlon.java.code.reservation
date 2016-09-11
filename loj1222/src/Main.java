/**
 * May 4, 2016 6:55:55 PM
 * PrjName: loj1222
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
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			g=new Graph((n+2<<1)+5, (n+2<<1)*(n+2<<1));
			for(int i=1;i<=n;i++){
				g.addedge(1, i+1, 1, 0);
				g.addedge(n+i+1, n+n+2, 1, 0);
				for(int j=1;j<=n;j++){
					int k=in.nextInt();
					g.addedge(i+1, n+j+1, 1, -k);
				}
			}
			flow=new MinCostMaxFlow(g, (n+2<<1)+5, (n+2<<1)*(n+2<<1), 1, n+n+2);
			out.println("Case "+(++cas)+": "+-1*flow.solve());
		}
		out.flush();
		out.close();
	}

}
class Edge {
	int v, f, w, nx;

	Edge(int _v, int _f, int _w, int _nx) {
		v = _v; f = _f; w = _w; nx = _nx;
	}
}

class Graph {
	int[] h;
	int n, m;
	Edge[] e;

	Graph(int _n, int _m) {
		n = _n; m = _m;
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
class spfa {
	final int inf = 0x3fffffff;
	int maxn, src, sink;
	Graph g;
	Queue<Integer> que;
	boolean[] inQue;
	int[] dis, prev, pree;

	public spfa(Graph _g, int _n, int _u, int _v) {
		g = _g; maxn = _n; src = _u; sink = _v;
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
class MinCostMaxFlow {
	final int inf = 0x3fffffff;
	int n, m;
	Graph g;
	spfa work;
	int num, src, sink;

	public MinCostMaxFlow(Graph _g, int _n, int _m, int _u, int _v) {
		n = _n; m = _m; g = _g; src = _u; sink = _v;
	}

	boolean findPath() {
		work = new spfa(g, n, src, sink);
		return work.get_res(sink) < inf;
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