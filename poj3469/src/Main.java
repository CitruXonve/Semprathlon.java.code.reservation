/**
 * May 6, 2016 12:42:40 PM
 * PrjName: poj3469
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
//	final static int maxm = 500010, maxn = 20010;
	final static double eps = 1e-3;
//	final static int inf = 0x3fffffff;

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
		dinic = new Dinic();
		int cas = 0;

		int n = in.nextInt();
		int m = in.nextInt();
		dinic.init();
		for (int i = 1; i <= n; i++) {
			dinic.addEdge(0, i, in.nextInt());
			dinic.addEdge(i, n + 1, in.nextInt());
		}
		for (int i = 0; i < m; i++) {
			dinic.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
		}
		out.println(dinic.maxflow(0,n+1));

		out.flush();
		out.close();
	}

}

class Dinic
{
    final int maxm=500010,maxn=20010;
    final int inf=0x3fffffff;
    class Edge{
        int to, cap, flow, next;
        //Edge(){}
        /*Edge(int _v,int _f,int _w,int _nx){
            to=_v;
            cap=_f;
            flow=_w;
            next=_nx;
        }*/
        void modify(int _v,int _f,int _w,int _nx){
            to=_v;
            cap=_f;
            flow=_w;
            next=_nx;
        }
    };
    Edge[] edge;
    int[] head;
    int edgenum;
    Dinic(){
        edge=new Edge[maxm];
        for(int i=0;i<maxm;i++)
            edge[i]=new Edge();
        head=new int[maxn];
        dist=new int[maxn];
        vis=new boolean[maxn];
    }
    void init(){
        edgenum = 0;
        Arrays.fill(head, -1);
    }
    void addEdge(int u, int v, int w)
    {
        edge[edgenum].modify(v, w, 0, head[u]);
        head[u] = edgenum++;
        edge[edgenum].modify(u, w, 0, head[v]);
        head[v] = edgenum++;
    }
    int[] dist; boolean[] vis;
    Queue<Integer> Q=new LinkedList<Integer>();
    boolean bfs(int s, int t)
    {
        Q.clear();
        Arrays.fill(dist, -1);
        Arrays.fill(vis, false);
        dist[s] = 0;
        Q.add(s);
        vis[s] = true;
        while(!Q.isEmpty())
        {
            int u = Q.poll();
            for(int i = head[u]; i != -1; i = edge[i].next)
            {
                Edge E = edge[i];
                if(!vis[E.to] && E.cap > E.flow)
                {
                    dist[E.to] = dist[u] + 1;
                    if(E.to == t) return true;
                    vis[E.to] = true;
                    Q.add(E.to);
                }
            }
        }
        return false;
    }
    int dfs(int x, int a, int t)
    {
        if(x == t || a == 0) return a;
        int flow = 0, f;
        for(int i = head[x]; i != -1; i = edge[i].next)
        {
            Edge E = edge[i];
            if(dist[E.to] == dist[x] + 1 && (f = dfs(E.to, Math.min(a, E.cap-E.flow), t)) > 0)
            {
                edge[i].flow += f;
                edge[i^1].flow -= f;
                flow += f;
                a -= f;
                if(a == 0) break;
            }
        }
        return flow;
    }
    int maxflow(int s, int t)
    {
        int flow = 0;
        while(bfs(s, t))
        {
            flow += dfs(s, inf, t);
        }
        return flow;
    }
};
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